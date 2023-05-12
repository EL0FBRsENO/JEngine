//#type vertex
#version 330 core
layout (location = 0) in vec3 aPos;
layout (location = 1) in vec4 aColor;
layout (location = 2) in vec2 aTexCoords;
layout (location = 3) in float aTexId;

uniform mat4 uProjection;
uniform mat4 uView;

out vec4 fColor;
out vec2 fTexCoords;
out float fTexId;

void main() {
    fColor = aColor;
    fTexCoords = aTexCoords;
    fTexId = aTexId;

    gl_Position = uProjection * uView * vec4(aPos, 1.0);
}

//#type fragment
#version 330 core

in vec4 fColor;
in vec2 fTexCoords;
in float fTexId;

const int num_zero = 0;
const int num_one = 1;
const int num_two = 2;
const int num_three = 3;
const int num_four = 4;
const int num_five = 5;
const int num_six = 6;
const int num_seven = 7;
const int num_eight = 8;
const int num_nine = 9;

uniform sampler2D uTextures[8];

out vec4 color;

void setColor(int n) {
    color = fColor * texture(uTextures[n], fTexCoords);
}

void main() {
    if (fTexId > 0) {
        // GLSL 3.3 doesn't support indexing non-contants in arrays, so we just use conditions :D!
        if (int(fTexId) == 1) {
            setColor(1);
        }else if (int(fTexId) == 2) {
            setColor(2);
        }else if (int(fTexId) == 3) {
            setColor(3);
        }else if (int(fTexId) == 4) {
            setColor(4);
        }else if (int(fTexId) == 5) {
            setColor(5);
        }else if (int(fTexId) == 6) {
            setColor(6);
        }else if (int(fTexId) == 7) {
            setColor(7);
        }
    } else {
        color = fColor;
    }
}
