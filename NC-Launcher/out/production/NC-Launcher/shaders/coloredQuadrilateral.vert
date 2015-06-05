#version 120

uniform mat4 modelView;

attribute vec3 attr_Position;
attribute vec3 attr_Color;

varying vec3 color;

void main() {
	color = attr_Color;
    gl_Position = gl_ProjectionMatrix * modelView * vec4(attr_Position, 1.0);
}