var greenImage = null;
var backImage = null;
var greenCanvas;
var backCanvas;
var resultCanvas;

function loadGreenImage() {
  var file = document.getElementById("greenfile");
  greenImage = new SimpleImage(file);
  greenCanvas = document.getElementById("greencan");
  greenImage.drawTo(greenCanvas);
}

function loadBackImage() {
  var file = document.getElementById("backfile");
  backImage = new SimpleImage(file);
  backCanvas = document.getElementById("backcan");
  backImage.drawTo(backCanvas);
}

function replaceGreenScreen() {
  //resulting image will be displayed in resultcan 
  resultCanvas = document.getElementById("resultcan");
  //pop-up message:
  alert("Replacing greenscreen");
  // resulting image:
  var result = new SimpleImage(greenImage.getWidth(), greenImage.getHeight());

  //for each pixel in greenImage:
  for (var pixel of greenImage.values()){
      // check if the current pixel is green (has more green than blue+red combined):
      if (pixel.getGreen() > pixel.getRed() + pixel.getBlue()) {
          var x = pixel.getX();
          var y = pixel.getY();
          var backPixel = backImage.getPixel(x,y);
          result.setPixel(x, y, backPixel);
      } //if it doesn't, don't replace the pixel:
      else{
          result.setPixel(pixel.getX(), pixel.getY(), pixel);
      }
   
  }
  result.drawTo(resultCanvas); 
}

function clearCanvas() {
  doClear(greenCanvas);
  doClear(backCanvas);
  doClear(resultCanvas);
}

function doClear(canvas) {
  var context = canvas.getContext("2d");
  context.clearRect(0,0,canvas.width,canvas.height);
}