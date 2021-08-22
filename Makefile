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