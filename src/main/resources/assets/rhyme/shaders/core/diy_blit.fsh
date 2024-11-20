#version 150
in vec2 texCoord;

uniform sampler2D att;
uniform vec4 ColorModulator;
out vec4 fragColor;

void main(){
    //if(texture(att, texCoord).a < 0.01) discard;
//    fragColor = vec4(1.0, 1.0, 1.0, 1.0);
        fragColor = texture(att, texCoord) * ColorModulator;
}
