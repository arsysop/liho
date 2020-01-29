package ru.arsysop.liho.file;

import java.util.function.Supplier;

public interface Extension extends Supplier<String> {

	default boolean equals(Extension another){
		 return get().equalsIgnoreCase(another.get());
	}

}
