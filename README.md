# image2arff
Converts an image to an ARFF file.

How to
======

Download image2arff.
```bash
git clone https://github.com/davips/image2arff
```

Install [SBT](http://www.scala-sbt.org/index.html).

Run image2arff for a given image file (e.g. `fig.bmp`).
```bash
cd image2arff
sbt -J-Xss200m "run file=fig.bmp"
```

Open the generated ARFF file (e.g. `fig.bmp.arff`) in your favorite data mining tool.

Example image
=============
!Image
(https://github.com/davips/image2arff/blob/master/fig.gif)
