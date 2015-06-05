#version 120

uniform sampler2D texture;

varying vec4 color;

void main(){
    gl_FragColor = color;
}