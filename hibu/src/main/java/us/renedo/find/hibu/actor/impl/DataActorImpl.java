package us.renedo.find.hibu.actor.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.util.Iterator;

import org.apache.camel.processor.validation.ValidatingProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import us.renedo.find.hibu.DomainSearch;
import us.renedo.find.hibu.actor.DataActor;
import us.renedo.find.hibu.actor.GenericActor;
import us.renedo.find.hibu.bo.DomainBo;
import us.renedo.find.hibu.entity.Domain;
import us.renedo.find.hibu.util.State;
import us.renedo.find.hibu.util.SyncRead;
import us.renedo.find.hibu.util.VarPool;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.ProxyServer;
import com.ning.http.client.Response;

import akka.actor.TypedActor;


public final class DataActorImpl extends GenericActor implements DataActor {

	@Override
	public void preStart() {}{		
		
	}
	
	@Override
	public void procNoAsync(String url,final Integer page,ApplicationContext appContext) {
		this.domainBo = (DomainBo)appContext.getBean("domainBo");
		
		Thread t = Thread.currentThread();
		t.setName("DataActor"+page);
		final ProxyServer ps = new ProxyServer(VarPool.PROXY, VarPool.PORT);
		System.out.println("Get:"+url);
		
		SyncRead srMain = new SyncRead();
		State.addPageWhitNoData(page);
		
			String content = srMain.getUrl(url, new Proxy(Proxy.Type.HTTP, new InetSocketAddress(VarPool.PROXY, VarPool.PORT)));
			
			Document document = Jsoup.parse(content);
			
			Elements elements = document.select("dd a.PaolClick");
			
			Iterator<Element> iElements = elements.iterator();
			
			while(iElements.hasNext()){
				Domain domain = null;
				
				Element ahrefDomain = iElements.next();
				String urlDomain = ahrefDomain.attr("href");
				System.out.println("Check:"+urlDomain);
				
				if(urlDomain.equals(VarPool.SHARP))continue;
				
				domain = domainBo.getByDomain(urlDomain);
				if(domain==null){
					domain = new Domain();
					domain.setDomain(urlDomain);
				}
				
				
				Element label = ahrefDomain.parent().parent().parent();
				//System.out.println(label.html());
				String name = null;
				String urlHref = null;
				try{
					name =label.child(0).child(2).child(0).child(0).html();
				}catch(Exception e){e.printStackTrace();}
				try{
					urlHref =label.child(0).child(2).child(0).attr("href");
				}catch(Exception e){/*e.printStackTrace();*/}
				if(name!=null){
					domain.setName(name);
					System.out.println("CheckName:"+domain.getName());
				}

				Element dir = null;
				try{
					dir =label.child(1);
					if(dir!=null){
						StringBuffer sbDir = new StringBuffer();
						sbDir.append(dir.child(0).html());
						sbDir.append(dir.child(1).html());
						sbDir.append(dir.child(2).html());
						sbDir.append(dir.child(3).html());
						sbDir.append(dir.child(4).html());
						domain.setDir(sbDir.toString());
						System.out.println("CheckDir:"+domain.getDir());
					}
				}catch(Exception e){e.printStackTrace();}
				
				if(urlHref!=null){
					System.out.println("CheckUrl:"+url);
					SyncRead sr = new SyncRead();
					String content2 = sr.getUrl(url, new Proxy(Proxy.Type.HTTP, new InetSocketAddress(VarPool.PROXY, VarPool.PORT)));
					
					Document documentTelf = Jsoup.parse(content2);
					Elements phones = documentTelf.select("p.m-business-card--phone");
					
					if(!phones.isEmpty()){
						try{
							domain.setTelf(phones.get(0).child(1).html());
							System.out.println("CheckTelf:"+domain.getTelf());
						}catch(Exception e){e.printStackTrace();}
					}

					
				}
				
				try{
					if(domain.getId()==null||domain.getId()<1){
						domainBo.save(domain);
					}else{
						domainBo.update(domain);
					}
				}catch(Exception e){e.printStackTrace();}
			}
			State.removePageWhitNoData(page);
		
	}
	
	
	@Override
	public void proc(String url,final Integer page) {
		Thread t = Thread.currentThread();
		t.setName("DataActor"+page);
		final ProxyServer ps = new ProxyServer(VarPool.PROXY, VarPool.PORT);
		System.out.println("Get:"+url);

		AsyncHttpClientConfig ahcConfig=new AsyncHttpClientConfig.Builder().setConnectTimeout(20000).build();
		final AsyncHttpClient asyncHttpClient = new AsyncHttpClient(ahcConfig);
		State.addPageWhitNoData(page);
		asyncHttpClient.prepareGet(url).setProxyServer(ps).execute(new AsyncCompletionHandler<Response>() {
			
			@Override
			public Response onCompleted(Response response) throws Exception {
				try{
					Thread t = Thread.currentThread();
					t.setName("AsyncHttpClient"+page);
					
					Document document = Jsoup.parse(response.getResponseBody());
					
					Elements elements = document.select("dd a.PaolClick");
					
					Iterator<Element> iElements = elements.iterator();
					
					while(iElements.hasNext()){
						Domain domain = null;
						
						Element ahrefDomain = iElements.next();
						String urlDomain = ahrefDomain.attr("href");
						System.out.println("Check:"+urlDomain);
						
						if(urlDomain.equals(VarPool.SHARP))continue;
						
						domain = domainBo.getByDomain(urlDomain);
						if(domain==null){
							domain = new Domain();
							domain.setDomain(urlDomain);
						}
						
						
						Element label = ahrefDomain.parent().parent().parent();
						//System.out.println(label.html());
						String name = null;
						String url = null;
						try{
							name =label.child(0).child(2).child(0).child(0).html();
						}catch(Exception e){e.printStackTrace();}
						try{
							url =label.child(0).child(2).child(0).attr("href");
						}catch(Exception e){e.printStackTrace();}
						if(name!=null){
							domain.setName(name);
							System.out.println("CheckName:"+domain.getName());
						}
	
						Element dir = null;
						try{
							dir =label.child(1);
						}catch(Exception e){e.printStackTrace();}
						if(dir!=null){
							StringBuffer sbDir = new StringBuffer();
							sbDir.append(dir.child(0).html());
							sbDir.append(dir.child(1).html());
							sbDir.append(dir.child(2).html());
							sbDir.append(dir.child(3).html());
							sbDir.append(dir.child(4).html());
							domain.setDir(sbDir.toString());
							System.out.println("CheckDir:"+domain.getDir());
						}
						
						if(url!=null){
							System.out.println("CheckUrl:"+url);
							SyncRead sr = new SyncRead();
							String content = sr.getUrl(url, new Proxy(Proxy.Type.HTTP, new InetSocketAddress(VarPool.PROXY, VarPool.PORT)));
							
							Document documentTelf = Jsoup.parse(content);
							Elements phones = documentTelf.select("p.m-business-card--phone");
							
							if(!phones.isEmpty()){
								try{
									domain.setTelf(phones.get(0).child(1).html());
									System.out.println("CheckTelf:"+domain.getTelf());
								}catch(Exception e){e.printStackTrace();}
							}
	
							
						}
						
						try{
							if(domain.getId()==null||domain.getId()<1){
								domainBo.save(domain);
							}else{
								domainBo.update(domain);
							}
						}catch(Exception e){e.printStackTrace();}
					}
					State.removePageWhitNoData(page);
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					asyncHttpClient.close();
				}
				return response;
			}
			
			@Override
		    public void onThrowable(Throwable t){
		        System.out.println("Error:"+t.getMessage());
		        State.addPageWhitOutReponse(page);
		    }
			
		});
		
	}

}
