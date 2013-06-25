Ming Server Core
----------------
Package: com.ming.server

Contains only files related to the Ming Server Core.
Designed to work with Jetty.

It provides the following services:

	1) General templating. Put templates in resources/templates.
	
	2) Serving static files. Put static files in resources/publicweb. Static files can refer to
	   templates using ${template.xyz} notation, which will be sourced from templates/publicweb.
	  
	3) Simple REST framework. To make a REST handler, simply extend ApiBase and override
	   one of the onGet(), onPost() etc methods.
	  
	4) Enables the Ming Presentation framework.
	   Note that widgets, styling, etc are not part of this framework. You can put
	   your own widgets, css, javascript etc in your resources/presentation folder and it will
	   be automatically handled under the Ming Presentation framework.
	   