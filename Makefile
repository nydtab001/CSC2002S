# binary search program makefile
# Hussein Suleman
# 27 March 2017

JAVAC=/usr/bin/javac

.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
DOCDIR=doc
ROOTDIR=.

SOURCELIST=$(shell find $(SRCDIR) -name '*.java' | sed "s,[.]/,,g")

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<


CLASSES=parallel.class \
	Main.class test.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)
default: $(CLASS_FILES)
	@javadoc -d $(DOCDIR) -linksource $(SOURCELIST)
clean:
	rm $(BINDIR)/*.class
	rm -r $(DOCDIR)/*
v1=
v2=
v3=
run:
    @java -cp bin Main ${v1} ${v2} ${v3}
run2:
    @java -cp bin test ${v1} ${v2} ${v3}