package com.hotmart.voices.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class EncodingService {

    public void encodingFile(String inputFilePath, String inputSecondAudioPath) throws IOException {

        String outputFilePath = "/caminho/do/arquivo_saida.mp4";

        FFmpeg ffmpeg = new FFmpeg();
        FFprobe ffprobe = new FFprobe();

        FFmpegProbeResult videoProbeResult = ffprobe.probe(inputFilePath);

        FFmpegStream audioStream = videoProbeResult.getStreams().stream()
                .filter(stream -> "audio".equals(stream.codec_type))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nenhuma stream de áudio encontrada"));

        String command = String.format(
                "-i %s -i %s -map 0:v -map 0:a:0 -map 1:a:0 -c:v copy -c:a copy " +
                        "-var_stream_map \"v:0 a:0,name:Portugues,agroup:audio,default:yes,language:por a:1,name:Ingles,agroup:audio,language:en\" " +
                        "-master_pl_name master.m3u8 -f hls -hls_time 10 -hls_list_size 0 " +
                        "-hls_segment_filename \"output_%%v_%%03d.ts\" playlist_%%v.m3u8 %s",
                inputFilePath,
                inputSecondAudioPath,
                outputFilePath
        );

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(inputFilePath)
                .setComplexFilter(command)
                .addOutput(outputFilePath)
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg);
        executor.createJob(builder).run();
    }
}
