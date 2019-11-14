package services;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheBatchTest {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		final CacheManager mCacheManager=
				CacheManager.newInstance(
						CacheBatchTest.class.getResource("/ehcache-batch.xml"));
		
		final Cache mCache=mCacheManager.getCache("batchCa");
		
		for(int i=0;i<30;i++){
			Element mE1=new Element((i+1),"value "+(i+1));
			mCache.put(mE1);
		}
		
		System.out.println("Enter the batch size : ");
		int mSize=sc.nextInt();
		
		List<Integer> mList=mCache.getKeys();
		Collections.sort(mList);
		//System.out.println("List "+mList);
		
		int i=0,start=0,end=0;
		if(mSize<=mList.size()){
			do{
				start=i;
				end=i+mSize;
				System.out.println("start : "+start+".......... end : "+end);
				System.out.println("Batch : "+mCache.getAll(mList.subList(start,end)));
				i+=mSize;
			}while((i+mSize)<=mList.size());
			
			if(end<mList.size()){
				System.out.println("Batch : "+mCache.getAll(mList.subList(end,mList.size())));
			}
		}
		else{
			System.out.println("Batch size entered exceeds!!");
		}
		
		
	}

}
