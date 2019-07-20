package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import shortcut.Shortcut;

public class ShortcutTest {
	@Test
	public void validComboKeys() {
		String keysAsString = "alt+shift+i";
		String description = "reduce variable from two lines to one";
		Set<Integer> keys = Stream.of(16,18,73).collect(Collectors.toSet());
		
		Shortcut shortcut = new Shortcut(keysAsString,description);
		assertTrue(keys.containsAll(shortcut.getKeys()));
		assertTrue(shortcut.getKeys().containsAll(keys));
		assertEquals(keysAsString, shortcut.getKeysAsString());
		assertEquals(description, shortcut.getDescription());
	}
	
	@Test
	public void validComboKeysWithRejectingSecondCombo() {
		String keysAsString = "alt+shift+i(a)";
		String description = "reduce variable from two lines to one";
		Shortcut shortcut = new Shortcut(keysAsString,description);
		List<Integer> expectedKeys = Stream.of(16,18,73).collect(Collectors.toList());
		
		assertTrue(expectedKeys.containsAll(shortcut.getKeys()));
		assertTrue(shortcut.getKeys().containsAll(expectedKeys));
		assertEquals(keysAsString, shortcut.getKeysAsString());
		assertEquals(description, shortcut.getDescription());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidComboKeys() {
		String keysAsString = "alt+shift+ia";
		String description = "reduce variable from two lines to one";
		
		new Shortcut(keysAsString,description);
	}
	
}
