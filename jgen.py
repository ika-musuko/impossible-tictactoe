#!/bin/python
from sys import argv
from os import path

fn = path.splitext(path.basename(argv[1]))[0]

f = """#!/bin/bash
find -name "*.java" > jsources
javac -nowarn @jsources
rm jsources
echo "<html><body><applet width='500' height='500' code='%s.class'></applet></body></html>" > TicTacToe.html""" % (fn)

with open("agen_%s.sh" % fn, "w") as h:
	h.write(f)

