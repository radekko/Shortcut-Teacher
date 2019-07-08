package shortcut;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ShortcutsFactory {
	private final List<Shortcut> shortcutsInfo;
	private Iterator<Shortcut> iterator;

	public ShortcutsFactory(Supplier<List<Shortcut>> shortcutsProducer) {
		this.shortcutsInfo = shortcutsProducer.get();
		this.iterator = prepareIterator();
	}

	public Optional<Shortcut> getNextShortcut() {
		if(isLackOfTasks())
			return Optional.empty();
		
		if (iterator.hasNext())
			return Optional.of(iterator.next());
		
		iterator = prepareIterator();
		return getNextShortcut();
	}

	private boolean isLackOfTasks() {
		return shortcutsInfo.isEmpty();
	}

	private Iterator<Shortcut> prepareIterator() {
		Collections.shuffle(shortcutsInfo);
		return shortcutsInfo.iterator();
	}

}
