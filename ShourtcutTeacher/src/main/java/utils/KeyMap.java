package utils;
import static java.awt.event.KeyEvent.*;

import java.util.HashMap;
import java.util.Map;

public class KeyMap {
	private static KeyMap INSTANCE;
	private final Map<String, Integer> keyMap;
	
	private KeyMap() {
		this.keyMap = createMap();
	}
	
	public static KeyMap getInstance() {
		if(INSTANCE == null)
			return new KeyMap();
		
		return INSTANCE;
	}
	
	public int getKey(String keyName) {
		if(!keyMap.containsKey(keyName))
			throw new IllegalArgumentException("Unsupported value: " + keyName);

		return keyMap.get(keyName);
	}
	
	private Map<String, Integer> createMap() {
	    Map<String,Integer> m = new HashMap<>();
	    m.put("enter", VK_ENTER);
	    m.put("space",VK_SPACE);
	    m.put("backspace",VK_BACK_SPACE);
	    m.put("ctrl",VK_CONTROL);
	    m.put("delete",VK_DELETE);
	    m.put("shift",VK_SHIFT);
	    m.put("alt",VK_ALT);
	    m.put("tab",VK_TAB);
	    m.put("up",VK_UP);
	    m.put("down",VK_DOWN);
	    m.put("left",VK_LEFT);
	    m.put("right",VK_RIGHT);
	    
	    m.put("a",VK_A);              
	    m.put("b",VK_B);           
	    m.put("c",VK_C);              
	    m.put("d",VK_D);              
	    m.put("e",VK_E);              
	    m.put("f",VK_F);              
	    m.put("g",VK_G);              
	    m.put("h",VK_H);              
	    m.put("i",VK_I);              
	    m.put("j",VK_J);              
	    m.put("k",VK_K);              
	    m.put("l",VK_L);              
	    m.put("m",VK_M);              
	    m.put("n",VK_N);              
	    m.put("o",VK_O);              
	    m.put("p",VK_P);              
	    m.put("q",VK_Q);              
	    m.put("r",VK_R);              
	    m.put("s",VK_S);              
	    m.put("t",VK_T);              
	    m.put("u",VK_U);              
	    m.put("v",VK_V);              
	    m.put("w",VK_W);              
	    m.put("x",VK_X);              
	    m.put("y",VK_Y);              
	    m.put("z",VK_Z); 
	    
	    m.put("pgUp", VK_PAGE_UP);
	    m.put("pgDown", VK_PAGE_DOWN);
	    m.put("f3", VK_F3);
	    m.put("f12", VK_F12);
	    m.put("dot",VK_PERIOD);
	    
	    return m;
	}
	
}
