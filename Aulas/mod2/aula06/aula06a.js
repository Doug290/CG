// TranslatedTriangle.js (c) 2012 matsuda
// Vertex shader program
  var VSHADER_SOURCE =
  // x' = x cosβ - y sinβ
  // y' = x sinβ + y cosβ　
  // z' = z
  'attribute vec4 a_Position;\n' +
  'uniform float u_CosB, u_SinB;\n' +
  'void main() {\n' +
  '  gl_Position.x = a_Position.x * u_CosB - a_Position.y * u_SinB;\n' +
  '  gl_Position.y = a_Position.x * u_SinB + a_Position.y * u_CosB;\n' +
  '  gl_Position.z = a_Position.z;\n' +
  '  gl_Position.w = 1.0;\n' +
  '}\n';


// Fragment shader program
var FSHADER_SOURCE =
  'precision mediump float;\n' +
  'uniform vec4 u_FragColor;\n' +  // unifor
  'void main() {\n' +
  '  gl_FragColor = u_FragColor;\n' +
  '}\n';

// The rotation angle
var ANGLE = 0.0; 

var g_colors = [];  // The array to store the color of a point

// The translation distance for x, y, and z direction
var Tx = 0.0, Ty = 0.5, Tz = 0.0;

function main() {
  // Retrieve <canvas> element
  var canvas = document.getElementById('webgl');

  // Get the rendering context for WebGL
  var gl = getWebGLContext(canvas);
  if (!gl) {
    console.log('Failed to get the rendering context for WebGL');
    return;
  }

  // Initialize shaders
  if (!initShaders(gl, VSHADER_SOURCE, FSHADER_SOURCE)) {
    console.log('Failed to intialize shaders.');
    return;
  }

  // Write the positions of vertices to a vertex shader
  var n = initVertexBuffers(gl);
  if (n < 0) {
    console.log('Failed to set the positions of the vertices');
    return;
  }

  var u_FragColor = gl.getUniformLocation(gl.program, 'u_FragColor');
  if (!u_FragColor) {
    console.log('Failed to get the storage location of u_FragColor');
    return;
  }

  

  // Specify the color for clearing <canvas>
  gl.clearColor(0, 0, 0, 1);

  // Clear <canvas>
  gl.clear(gl.COLOR_BUFFER_BIT);
 
  var rgba;
  // Draw the rectangle
  for(var i=0; i < 6; i++)
  {

      // // Pass the data required to rotate the shape to the vertex shader
    var radian = Math.PI * ANGLE / 180.0; // Convert to radians
    var cosB = Math.cos(radian);
    var sinB = Math.sin(radian);

    var u_CosB = gl.getUniformLocation(gl.program, 'u_CosB');
    var u_SinB = gl.getUniformLocation(gl.program, 'u_SinB');
    if (!u_CosB || !u_SinB) {
      console.log('Failed to get the storage location of u_CosB or u_SinB');
      return;
    }
    gl.uniform1f(u_CosB, cosB);
    gl.uniform1f(u_SinB, sinB);

    //Select Color
    rgba = g_colors[i];
    gl.uniform4f(u_FragColor, rgba[0], rgba[1], rgba[2], rgba[3]);
    
    //Draw
    gl.drawArrays(gl.TRIANGLES, 0, n);

    ANGLE = ANGLE + 60;
  }
  
}

function initVertexBuffers(gl) {
  var vertices = new Float32Array([
    -0.4, 0.4, 0,   -0.8, -0.4, 0,   0.0, -0.4, 0
  ]);


  var n = 3; // The number of vertices

  // Create a buffer object
  var vertexBuffer = gl.createBuffer();
  if (!vertexBuffer) {
    console.log('Failed to create the buffer object');
    return -1;
  }

  // Bind the buffer object to target
  gl.bindBuffer(gl.ARRAY_BUFFER, vertexBuffer);
  // Write date into the buffer object
  gl.bufferData(gl.ARRAY_BUFFER, vertices, gl.STATIC_DRAW);


  // Assign the buffer object to the attribute variable
  var a_Position = gl.getAttribLocation(gl.program, 'a_Position');

  if (a_Position < 0) {
    console.log('Failed to get the storage location of a_Position');
    return -1;
  }
  gl.vertexAttribPointer(a_Position, 3, gl.FLOAT, false, 0, 0);

  // Enable the assignment to a_Position variable
  gl.enableVertexAttribArray(a_Position);


  g_colors.push([1.0, 0.0, 0.0, 1.0]);  // Red
  g_colors.push([0.0, 1.0, 0.0, 1.0]);  // Green
  g_colors.push([1.0, 1.0, 1.0, 1.0]);  // White
  g_colors.push([1.0, 0.0, 0.0, 1.0]);  // Red
  g_colors.push([0.0, 1.0, 0.0, 1.0]);  // Green
  g_colors.push([1.0, 1.0, 1.0, 1.0]);  // White
  


  return n;
}
