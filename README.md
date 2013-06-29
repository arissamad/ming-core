Ming Server Core
================
Package: com.ming.server

Ming Core - A lightweight, modern java-based web framework. Very simple support for REST, static files, and Ming Presentation Framework.

Contains only files related to the Ming Server Core.
Designed to work with Jetty.

It provides the following services:

	1) General templating. Put templates in resources/templates.
	
	2) Serving static files. Put static files in resources/publicweb. Static files can refer to templates using ${template.xyz} notation, which will be sourced from templates/publicweb.
	  
	3) Simple REST framework. To make a REST handler, simply create a class in <your-package>.api.<folder-name>.<your-class>, extend ApiBase and annotate with @GET, @POST, etc.
	  
	4) Enables the Ming Presentation framework.
	Note that widgets, styling, etc are not part of this framework. You can put
	your own widgets, css, javascript etc in your resources/presentation folder and it will be automatically handled under the Ming Presentation framework.


HTML Templates
--------------
Files located in resources/templates/publicweb are html templates.
HTML files located in resources/publicweb can reference these templates. For example:

${template.header}

This will be filled with the contents of the file in templates/publicweb/header.html


Ming Presentation Framework
---------------------------

The Ming Presentation Framework defines how JavaScript, CSS or HTML Fragments are handled.
It itself does not include any presentation-specific JavaScript, CSS or HTML Fragment files,
nor any general-purpose utilities.

Instead, you put your own project-specific JavaScript, CSS and HTML Fragment
files in "/resources/presentation".

The Ming Presentation Framework does provide some services for your files. Namely:

	1) Mixin support
	2) Widget support

How to use it
-------------
Ming Presentation works hand-in-hand with your static web assets located in "/resources/publicweb".
You can enable Ming Presentation on a particular page simply by loading the dynamically-generated
"control.js" file and jquery as follows:

	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	<script src="/control.js"></script>
	
When the page loads, the Ming Server looks through all your files in "/resources/presentation", 
combines them and gives them cache-safe MD5 names. It then instructs the page to load those assets.

You now launch your javascript code with this code block:

	<script>
		function onMingStart() {
    		log("onMingStart() called.");
		}
	</script>

Do anything you want in that code block. Ming Presentation framework will only call 
"onMingStart()" when all the presentation assets have loaded.

Advantages
----------
	1) In Development Mode, the files are rescanned every time control.js is loaded, allowing
	for easy testing as you make changes. You can add new files and make changes without needing
	to recompile, rebuild etc.
	
	2) In Production Mode, the files are cached for efficient retrieval.
	
	3) Caching is completely safe and efficient due to the use of MD5 names.