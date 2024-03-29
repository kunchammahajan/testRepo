package services;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheCrud {

	public static void main (String args[]){
	
		// Create a cache manager using the factory method
	    // AND specify the new configuration file
	    final CacheManager cacheManager = 
	          CacheManager.newInstance(
	            CacheCrud.class.getResource("/ehcache-crud.xml"));
	    
	  // Get the "crud" cache from the cache manager...
	    final Cache mCache = cacheManager.getCache("crud");
	    
	 // Set up the first data element...
	    final String myKey = "My Key";
	    final String myData = "My Data";
	    final Element createElement = new Element(myKey, myData);
	    
	 // CREATE data using the put(Element) method...
	    mCache.put(createElement);
	    System.out.println("Created data: " + createElement);
	    
	    // READ data using the get(Object) method...
        Element readElement = mCache.get(myKey);
        System.out.println("Read data:    " + readElement);
        
     // Check to make sure the data is the same...
        if (! myData.equals(readElement.getObjectValue())) {
            throw new RuntimeException("My data doesn't match!");
        }
        
     // UPDATE data by mapping a new value to the same key...
        final String myNewData = "My New Data";
        final Element updateElement = new Element(myKey, myNewData);
        mCache.put(updateElement);
        System.out.println("Updated data: " + updateElement);
        System.out.println("Updated data: " + mCache.get(myKey).getObjectValue());
        
     // Test to see that the data is updated...
        readElement = mCache.get(myKey);
        if (! myNewData.equals(readElement.getObjectValue())) {
            throw new RuntimeException("My data doesn't match!");
        }
        
     // DELETE data using the remove(Object) method...
        final boolean wasRemoved = mCache.remove(myKey);
        System.out.println("Removed data: " + wasRemoved);
        if (! wasRemoved) {
            throw new RuntimeException("My data wasn't removed!");
        }
        
     // Be polite and release the CacheManager resources...
        cacheManager.shutdown();
	}
	
}
