#version 120

uniform sampler2D texture;

varying vec2 texturePos;

void main(){
    gl_FragColor = texture2D(texture, texturePos);
}