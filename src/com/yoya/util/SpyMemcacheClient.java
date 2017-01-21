/**
 *
 * 版权声明 厦门网中网软件有限公司, 版权所有 违者必究
 *
 *<br> Copyright:	Copyright (c) 2014
 *<br> Company:		厦门网中网软件有限公司
 *<br> @author		baihw
 *————————————————————————————————
 *修改记录
 *    修改者：
 *    修改时间：
 *    修改原因：
 *————————————————————————————————
 */
package com.yoya.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.CASValue;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.ConnectionObserver;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;



/**
 * 程序描述:
 * @author		baihw
 */

public class SpyMemcacheClient
{
	// 日志处理对象
	//private static final ILogger _LOG = LoggerFactory.getLogger( SpyMemcacheClient.class ) ;
	
	private final String _HOSTS ;
	private final String _USER ;
	private final String _PASSWORD ;
	private MemcachedClient _client ;
	
	/**
	 * 构造函数
	 * @param hosts  可用的主机地址列表
	 */
	public SpyMemcacheClient( String hosts )
	{
		this( hosts, null, null ) ;
	}

	/**
	 * 构造函数
	 * @param hosts  	可用的主机地址列表
	 * @param user 		主机用户名
	 * @param password　	主机密码
	 */
	public SpyMemcacheClient( String hosts, String user, String password )
	{
		this._HOSTS = hosts ;
		this._USER = user ;
		this._PASSWORD = password ;
		this._client = buildClient() ;
	}
	
	/**
	 * 构建一个 MemcachedClient 对象
	 * @return
	 */
	private MemcachedClient buildClient()
	{
		MemcachedClient client = null ;
		try
		{
			
			ConnectionFactoryBuilder cfBuilder = new ConnectionFactoryBuilder() ;
			cfBuilder.setDaemon(true);
			cfBuilder.setProtocol( Protocol.BINARY ) ;
			if( null != _USER && null != _PASSWORD )
			{
				AuthDescriptor authDesc = new AuthDescriptor( new String[]{"PLAIN"}, 
						new PlainCallbackHandler( _USER, _PASSWORD ) ) ;
				cfBuilder.setAuthDescriptor( authDesc ) ;
			}
			client = new MemcachedClient( cfBuilder.build(), AddrUtil.getAddresses( _HOSTS ) ) ;
			client.addObserver( new ConnectionObserver()
			{
				
				@Override
				public void connectionLost( SocketAddress socketAddress )
				{
					System.out.println("lost... SocketAddress:" + socketAddress.toString());
					//_LOG.info( "lost... SocketAddress:" + socketAddress.toString() ) ;
				}
				
				@Override
				public void connectionEstablished( SocketAddress socketAddress, int reconnectCount )
				{
					//_LOG.info
					System.out.println( "Established... SocketAddress:" + socketAddress.toString() + ", reconnectCount:" + reconnectCount ) ;
				}
			} );
		}catch( IOException e )
		{
			//_LOG.error
			System.out.println( e.getMessage() ) ;
		}
		return client ;
	}
	
	/**
	 * 当存储空间中不存在键相同的数据时增加，如果指定的键名已经存在，则增加失败。
	 * @param _$key　	键名
	 * @param _$value　	数据
	 * @param _$expire 	过期时间，单位：秒，最大不能超过30天：60 * 60 * 24 * 30
	 * @return
	 */
	public boolean add( String _$key, Object _$value, int _$expire )
	{
		Future<Boolean> result = _client.add( _$key, _$expire, _$value ) ;
		return getFutureValue( result ) ;
	}
	
	/**
	 * 修改一个缓存对象，无论何种情况下都存储指定键名数据。
	 * @param _$key　	键名
	 * @param _$value　	数据
	 * @param _$expire 	过期时间，单位：秒，最大不能超过30天：60 * 60 * 24 * 30
	 * @return
	 */
	public boolean set( String _$key, Object _$value, int _$expire )
	{
		Future<Boolean> result = _client.set( _$key, _$expire, _$value ) ;
		return getFutureValue( result ) ;
	}
	
	/**
	 * 替换一个缓存对象，如果指定键名存在，则替换指定键名数据。仅当存储空间中存在键相同的数据时才保存。
	 * @param _$key　	键名
	 * @param _$value　	数据
	 * @param _$expire 	过期时间，单位：秒，最大不能超过30天：60 * 60 * 24 * 30
	 * @return
	 */
	public boolean replace( String _$key, Object _$value, int _$expire )
	{
		Future<Boolean> result = _client.replace( _$key, _$expire, _$value ) ;
		return getFutureValue( result ) ;
	}
	
	/**
	 * 删除指定键名的缓存数据
	 * @param _$key　	键名
	 * @return
	 */
	public boolean delete( String _$key )
	{
		Future<Boolean> result = _client.delete( _$key ) ;
		return getFutureValue( result ) ;
	}
	
	/**
	 * 获取指定键名对应的数据
	 * @param _$key　	键名
	 * @return
	 */
	public Object get( String _$key )
	{
		return _client.get( _$key ) ;
	}
	
	/**
	 * 获取指定键名对应的数据并重置其过期时间。
	 * @param key 		键名
	 * @param expire 	过期时间
	 * @return
	 */
	public Object getAndTouch( String key, int expire )
	{
		CASValue<Object> casValue = _client.getAndTouch( key, expire ) ;
		return null == casValue ? null : casValue.getValue() ;
	}
	
	/**
	 * 异步获取指定键名的数据
	 * @param _$key 	键名
	 * @return
	 */
	public Object asyncGet( String _$key )
	{
		Future<Object> result = _client.asyncGet( _$key ) ;
		return getFutureValue( result ) ;
	}
	
	/**
	 * 获取多个键名的数据
	 * @param _$keys 	键名数组
	 * @return
	 */
	public Map<String, Object> multiGet( String[] _$keys )
	{
		return _client.getBulk( _$keys ) ;
	}
	
	/**
	 * 异步获取多个键名的数据
	 * @param _$keys 	键名数组
	 * @return
	 */
	public Map<String, Object> asyncMultiGet( String _$keys )
	{
		Future<Map<String, Object>> result = _client.asyncGetBulk( _$keys ) ;
		return getFutureValue( result ) ;
	}
	
	public long increment( String _$key, int _$by, long _$defaultValue, int _$expire )
	{
		return _client.incr( _$key, _$by, _$defaultValue, _$expire ) ;
	}
	
	public long decrement( String _$key, int _$by, long _$defaultValue, int _$expire )
	{
		return _client.decr( _$key, _$by, _$defaultValue, _$expire ) ;
	}
	
	public long asyncDecrement( String _$key, int _$by )
	{
		Future<Long> result = _client.asyncDecr( _$key, _$by ) ;
		return getFutureValue( result ) ;
	}
	
	/**
	 * 获取异步任务的数据
	 * @param _$future
	 * @return
	 */
	private <T> T getFutureValue( Future<T> _$future )
	{
		T result = null ;
		try
		{
			result = _$future.get( 5, TimeUnit.SECONDS );
		}catch( InterruptedException e )
		{
			_$future.cancel( false ) ;
			//_LOG.error
			System.out.println( e.getMessage() ) ;
		}catch( ExecutionException e )
		{
			_$future.cancel( false ) ;
			//_LOG.error
			System.out.println( e.getMessage() ) ;
		}catch( TimeoutException e )
		{
			_$future.cancel( false ) ;
			//_LOG.error
			System.out.println( e.getMessage() ) ;
		}
		return result ;
	}

	/**
	 * 终止客户端连接.
	 */
	public void stop()
	{
		if( null != _client )
			_client.shutdown() ;
	}
	
	/**
	 * 打印数据
	 */
	public void printStats()
	{
		OutputStream out = System.out ;
		Map<SocketAddress, Map<String, String>> statMap = _client.getStats() ;
		StringBuffer sb = new StringBuffer() ;
		Iterator<SocketAddress> saSet = statMap.keySet().iterator() ;
		while( saSet.hasNext() )
		{
			SocketAddress sa = saSet.next() ;
			sb.append( sa.toString() ).append( "\n" ) ;
			Map<String, String> dataMap = statMap.get( sa ) ;
			Iterator<String> dataKeys = dataMap.keySet().iterator() ;
			while( dataKeys.hasNext() )
			{
				String dataKey = dataKeys.next() ;
				String dataVal = dataMap.get( dataKey ) ;
				sb.append( "  key=" ).append( dataKey ).append( ";value=" ).append( dataVal ).append( "\n" ) ;
			}
			sb.append( "\n" ) ;
		}
		try
		{
			out.write( sb.toString().getBytes() ) ;
			out.flush() ;
		}catch( IOException e )
		{
			//_LOG.error
			System.out.println( e.getMessage() ) ;
		}
	}

}  // End class