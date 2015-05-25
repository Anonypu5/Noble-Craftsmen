#version 120

<<<<<<< HEAD
attribute vec3 position;

varying vec3 pos;

void main(){
	pos = position;
    gl_Position = vec4(position, 1.0);
=======
uniform mat4 modelView;

attribute vec3 attr_Position;
attribute vec2 texCoord;

varying vec2 texturePos;

void main() {
	texturePos = texCoord;
    gl_Position = gl_ProjectionMatrix * modelView * vec4(attr_Position, 1.0);
>>>>>>> origin/master
}