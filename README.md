# LiHo
is a configurable and pluggale **li**cense **h**eader validation t**o**ol.

Specially dedicated [section](https://www.eclipse.org/projects/handbook/#ip-copyright-headers) 
in the [Eclipse Foundation Project Handbook](https://www.eclipse.org/projects/handbook/) prescribes 
how should we form license header for all files in an eclipse project 
(who's format supports comment this way or another). If you develop a project under Eclipse Foundation roof,
 you must follow  the guide and keep your license headers in accordance with the section.  

It appears to be practically hard to keep track on all the files in a project with long history.

So, here is the LiHo guy, who's intended to assist us here. 

It analyses the given source set according to some configuration and produce an issue 
for each discrepancy it finds between the guide and a source.   

You are looking at the core library, which provides infrastructure api and proper validations implementations. 

It can be
 - plugged to IDE with an eclipse plug-in, which applies the guy on *build* phase and report to *Problems view*
 - involved on a CI pipeline (say, by a GitHub Action)




