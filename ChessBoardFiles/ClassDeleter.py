import os

for entry in os.scandir():
    if entry.is_file():
    	if entry.name.endswith('.class'):
    		os.remove(entry.name)
