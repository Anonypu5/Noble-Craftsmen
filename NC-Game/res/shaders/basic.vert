#version 120

uniform mat4 modelView;

attribute vec3 attr_Position;
attribute vec2 texCoord;

varying vec2 texturePos;

void main() {
	texturePos = texCoord;
    gl_Position = gl_ProjectionMatrix * modelView * vec4(attr_Position, 1.0);
}