This example minifies and concatenates both Javascript and CSS files
using the YUI Compressor library and its ant plugin.  Despite having
a pom.xml for the project, you'll note that it really is ant driven
as there is a build.xml at the root and the yui jars are in the lib
folder.

To run the example, just run ant against build.xml.  A 'dist' folder
will be created inside root, and the compressed files will show up
there.
