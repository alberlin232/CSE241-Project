JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  CLI.java \
		  Test.java \
		  Database.java \
		  Apartment.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java -cp :ojdbc8.jar CLI

test: 
	java -cp :ojdbc8.jar Test