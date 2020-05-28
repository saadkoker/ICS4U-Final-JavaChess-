import tkinter as tk
import requests
from PIL import Image, ImageTk
import subprocess



app = tk.Tk()
app.title('Java Chess By Recursive Games')
app.iconbitmap('knight1.ico')

def startAIGame():
    subprocess.run('javac Main.java', shell=True)
    app.destroy()
    subprocess.run('java Main', shell=True)

def startTwoPersonGame():
    subprocess.run('javac Main.java', shell=True)
    subprocess.run('java Main', shell=True)
    
    
HEIGHT = 400
WIDTH = 715

C = tk.Canvas(app, height=HEIGHT, width=WIDTH)
background_image= tk.PhotoImage(file='./startScreen.png')
background_label = tk.Label(app, image=background_image)
background_label.place(x=0, y=0, relwidth=1, relheight=1)

submit = tk.Button(app, text='Two Player', font=40, command=lambda: startTwoPersonGame())
submit.place(x=30, y=255, relheight=0.1, relwidth=0.2)

submit2 = tk.Button(app, text='Player Vs. AI', font=40, command=lambda: startAIGame())
submit2.place(x=30, y=155, relheight=0.1, relwidth=0.2)

C.pack()

app.mainloop()
