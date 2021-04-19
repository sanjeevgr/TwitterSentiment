import sys
import os
import re
import sys

def createTruthFile(state, gdp, gdpG):
    filename = "TweetData/training/" + state + ".txt"
    with open(filename, "w") as f:
        f.write(gdp + "," + gdpG)

createTruthFile("Washington", "612996.5", "4.9")