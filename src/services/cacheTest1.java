package services;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.*;

public class cacheTest1 {

	public static void main(String[] args){
		// Create a cache manager
	    final CacheManager cacheManager = new CacheManager();
	   // cacheManager.getCacheManager("abcTest");
	    
	    // create the data store called "hello-world"
	    final Cache mCache = cacheManager.getCache("hello-world");
	 //   System.out.println(mCache.get("101"));
	    
	    // create a key to map the data to
	    final String key = "greeting";

	    // Create a data element
	    final Element putGreeting = new Element(key, "Hello, World!");

	    // Put the element into the data store
	    mCache.put(putGreeting);

	    // Retrieve the data element
	    final Element getGreeting = mCache.get(key);

	    // Print the value
	    System.out.println(getGreeting.getObjectValue());
	}
	
    
}
