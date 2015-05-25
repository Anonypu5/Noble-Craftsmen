#version 120

<<<<<<< HEAD
varying vec3 pos;

void main(){
    gl_FragColor = vec4(pos, 1.0);
=======
uniform sampler2D texture;

varying vec2 texturePos;

void main(){
    gl_FragColor = texture2D(texture, texturePos);
>>>>>>> origin/master
}