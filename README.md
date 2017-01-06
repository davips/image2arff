# image2arff
Converts an image to an ARFF file.
Please cite accordingly [![DOI](https://zenodo.org/badge/DOI/10.5281/zenodo.232681.svg)](https://doi.org/10.5281/zenodo.232681).

You have two options to use the program. You can clone the repository (recommended for complete user experience) or use the standalone JAR file.

Option 1 - standalone JAR file
------------------------------
Download the latest [JAR file](https://github.com/davips/image2arff/releases/).

Run
```bash 
java -Xss200m -jar image2arff-assembly-1.0.jar file=new-iris.gif
```

Option 2 - clone the repository
-------------------------------
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
-------------
![Image](https://github.com/davips/image2arff/blob/master/fig.gif)
