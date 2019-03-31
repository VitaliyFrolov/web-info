# web-info
Glassfish server domain search application.
It gives information about hostname, path, context-root, applications and administrator's ports. It works only on Unix.

Example JSON:
{"Host":"balalaral","Home":"/home/vital/glassfish5"}
{domain1={context-root=/javainfo_war_exploded, PortAPP=8080, PortAdmin=4848}, info={context-root=/findInfo, PortAPP=2526, PortAdmin=2525}}

For correct working of application you have to create domain and deploy webinfo.war on your Glassfish web-server.
Also you have to export this variable. 

user@pc# export GLASSFISH_HOME=/home/vital/glassfish5
