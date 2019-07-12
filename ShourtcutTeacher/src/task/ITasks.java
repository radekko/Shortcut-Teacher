package task;

import java.io.FileNotFoundException;

public interface ITasks {
	 Task getNextTask() throws FileNotFoundException;
}