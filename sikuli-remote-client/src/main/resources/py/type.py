from sikuli.Sikuli import Screen, KEY_CTRL, Key
scr = Screen()
scr.type(psc, "a", KEY_CTRL)
scr.type(Key.BACKSPACE)
scr.type(psc, content)