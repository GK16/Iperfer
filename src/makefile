JFLAGS = -g
JC = javac
J = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Iperfer.java \
	Server.java \
	Client.java

default: classes

classes: $(CLASSES:.java=.class)

client: classes
	$(J) Iperfer -c -h localhost -p 1025 -t 5

server: classes
	$(J) Iperfer -s -p 1025

clean:
	$(RM) *.class