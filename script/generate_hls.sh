#!/bin/bash

# verifica se passou o parametro
if [ "$#" -ne 1 ]; then
    echo "Forneça o video com as duas faixas de audio"
    echo "Exemplo: ./generate_hls.sh video.mp4"
    exit 1
fi

# Verifica se o ffmpeg está instalado
if ! command -v ffmpeg &> /dev/null; then
    echo "O comando 'ffmpeg' não foi encontrado."
    echo "Por favor, instale o ffmpeg para prosseguir."
    echo "Instruções de instalação podem variar de acordo com o sistema operacional."
    exit 1
fi

input1=$1

ffmpeg -i "$input1" \
-map 0:v -map 0:a:0 -map 0:a:1 \
-c:v copy -c:a copy -var_stream_map "v:0 a:0,name:Portugues,agroup:audio,default:yes,language:por a:1,name:Ingles,agroup:audio,language:en" \
-master_pl_name master.m3u8 -f hls -hls_time 10 -hls_list_size 0 -hls_segment_filename "output_%v_%03d.ts" playlist_%v.m3u8
