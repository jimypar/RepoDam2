#ifdef GL_ES
    #define PRECISION mediump
    precision PRECISION float;
    precision PRECISION int;
#else
    #define PRECISION
#endif

#define SEQUENCE_LENGTH 24.0
#define FPS 60.

varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform float u_speed;
uniform float u_time;


//////////////////////////////////////////////////////////////////////////////////////////
//
//	 OLD TV SHADER
//
//	 by Tech_
//
//////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////

#define COLOR_GRADING false
#define FILM_STRIPES true
#define FILM_TEXTURE true
#define FILM_VIGNETTE true
#define FILM_GRAIN true
#define FLICKER false		// Disabled, was too strong on some devices, you may try it
#define FILM_DIRT false
#define DESATURATION false

//////////////////////////////////////////////////////////////////////////////////////////

float luma(vec3 color)
{
    return dot(color, vec3(0.2126, 0.7152, 0.0722));
}

vec3 saturate(vec3 color, float adjustment)
{
    vec3 intensity = vec3(luma(color));
    return mix(intensity, color, adjustment);
}

float flicker(in vec2 uv, float amount) 
{
    uv *= 0.0001;
	return mix(pow(cos(uv.y * 100.2 + u_time * 80.), 0.4), 1., 1. - amount);
}

float filmStripes(in vec2 uv, float amount) 
{
    float stripes;
    float mask = cos(uv.x - cos(uv.y + u_time) + sin(uv.x * 10.2) - cos(uv.x * 60. + u_time)) + sin(uv.y * 2.);
    mask += flicker(uv, 1.);
    
    if(fract(uv.x + u_time) >= 0.928 && fract(uv.x + u_time) <= 0.929) 
    {
    	stripes = sin(uv.x * 4300. * sin(uv.x * 102.)) * mask;
    }
    if(fract(uv.x + fract(1. - u_time)) >= 0.98 + fract(u_time) && fract(uv.x + fract(u_time / 2. + sin(u_time / 2.))) <= 0.97 + fract(u_time + 0.2)) 
    {
    	stripes = sin(uv.x * 4300. * sin(uv.x * 102.)) * mask;
    }
    if(fract(uv.x + fract(- u_time * 1. + sin(u_time / 2.))) >= 0.96 + fract(u_time) && fract(uv.x + fract(u_time / 2. + sin(u_time / 2.))) <= 0.95 + fract(u_time + 0.2)) 
    {
    	stripes = sin(uv.x * 4300. * sin(uv.x * 102.)) * mask;
    }
    if(fract(uv.x + fract(- u_time * 1. + sin(u_time / 2.))) >= 0.99 + fract(u_time) && fract(uv.x + fract(u_time / 2. + sin(u_time / 2.))) <= 0.98 + fract(u_time + 0.2)) 
    {
    	stripes = sin(uv.x * 4300. * sin(uv.x * 102.)) * mask;
    }
    
    stripes = 1. - stripes;
    
   	return mix(1., stripes, amount);
}

float filmGrain(in vec2 uv, float amount) 
{
    float grain = fract(sin(uv.x * 100. * uv.y * 524. + fract(u_time)) * 5000.);
    float w = 1.;
	return mix(w, grain, amount);
}

float vignette(in vec2 uv) 
{
	uv *=  1.0 - uv.yx;
    float vig = uv.x*uv.y * 15.0;
    return clamp(pow(vig, 1.) * 1., 0., 1.);
}

vec3 reinhard(in vec3 color) 
{
	return color / (1. + color);
}



void main ()
{
    // Normalized pixel coordinates (from 0 to 1)
  	vec2 uv = v_texCoords;

    vec3 col = texture2D(u_texture, uv).rgb;
    
    if(COLOR_GRADING) 
    {
        col *= luma(col);
        col *= 1.9;
        col = col / 1.8 + 0.12;
    }

    if(FILM_STRIPES) 
    {
        col += 1. - filmStripes(uv, 0.07);
        col += 1. - filmStripes(uv + uv, 0.05);
    }

   
    if(FILM_VIGNETTE) 
    {
        col *= vignette(uv) * 1.1;
    }

    if(FILM_GRAIN) 
    {
        col *= filmGrain(uv, 0.16);
    }

    if(FLICKER) 
    {
        col *= flicker(uv, 0.1);
    }

    if(DESATURATION) 
    {
        col = saturate(col, 0.);
    }

    if(COLOR_GRADING) 
    {
        col.r *= 1.01;
        col.g *= 1.02;
    }
    
    // Output to screen
    gl_FragColor = vec4(col, 1.);
}