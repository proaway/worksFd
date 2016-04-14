/**
 * MemCached Java client
 * Copyright (c) 2007
 *
 * This module is Copyright (c) 2007 Greg Whalin, Richard Russo
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later
 * version.
 *
 * This library is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.  See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA
 *
 * @author Greg Whalin <greg@meetup.com> 
 * @author Richard 'toast' Russo <russor@msoe.edu>
 * @author Kevin Burton <burton@peerfear.org>
 * @author Robert Watts <robert@wattsit.co.uk>
 * @author Vin Chawla <vin@tivo.com>
 * @version 1.5
 */
package com.fd.memcached;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * This is a Java client for the memcached server available from
 *  <a href="http:/www.danga.com/memcached/">http://www.danga.com/memcached/</a>.
 * <br/> 
 * Supports setting, adding, replacing, deleting compressed/uncompressed and<br/>
 * serialized (can be stored as string if object is native class) objects to memcached.<br/>
 * <br/>
 * Now pulls SockIO objects from SockIOPool, which is a connection pool.  The server failover<br/>
 * has also been moved into the SockIOPool class.<br/>
 * This pool needs to be initialized prior to the client working.  See javadocs from SockIOPool.<br/>
 * <br/>
 * Some examples of use follow.<br/>
 * <h3>To create cache client object and set params:</h3>
 * <pre> 
 *	MemCachedClient mc = new MemCachedClient();
 *
 *	// compression is enabled by default	
 *	mc.setCompressEnable(true);
 *
 *	// set compression threshhold to 4 KB (default: 15 KB)	
 *	mc.setCompressThreshold(4096);
 *
 *	// turn on storing primitive types as a string representation
 *	// Should not do this in most cases.	
 *	mc.setPrimitiveAsString(true);
 * </pre>	
 * <h3>To store an object:</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	String key   = "cacheKey1";	
 *	Object value = SomeClass.getObject();	
 *	mc.set(key, value);
 * </pre> 
 * <h3>To store an object using a custom server hashCode:</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	String key   = "cacheKey1";	
 *	Object value = SomeClass.getObject();	
 *	Integer hash = new Integer(45);	
 *	mc.set(key, value, hash);
 * </pre> 
 * The set method shown above will always set the object in the cache.<br/>
 * The add and replace methods do the same, but with a slight difference.<br/>
 * <ul>
 * 	<li>add -- will store the object only if the server does not have an entry for this key</li>
 * 	<li>replace -- will store the object only if the server already has an entry for this key</li>
 * </ul> 
 * <h3>To delete a cache entry:</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	String key   = "cacheKey1";	
 *	mc.delete(key);
 * </pre> 
 * <h3>To delete a cache entry using a custom hash code:</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	String key   = "cacheKey1";	
 *	Integer hash = new Integer(45);	
 *	mc.delete(key, hashCode);
 * </pre> 
 * <h3>To store a counter and then increment or decrement that counter:</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	String key   = "counterKey";	
 *	mc.storeCounter(key, new Integer(100));
 * </pre> 
 * <h3>To store a counter and then increment or decrement that counter with custom hash:</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	String key   = "counterKey";	
 *	Integer hash = new Integer(45);	
 *	mc.storeCounter(key, new Integer(100), hash);
 * </pre> 
 * <h3>To retrieve an object from the cache:</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	String key   = "key";	
 *	Object value = mc.get(key);	
 * </pre> 
 * <h3>To retrieve an object from the cache with custom hash:</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	String key   = "key";	
 *	Integer hash = new Integer(45);	
 *	Object value = mc.get(key, hash);	
 * </pre> 
 * <h3>To retrieve an multiple objects from the cache</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	String[] keys      = { "key", "key1", "key2" };
 *	Map&lt;Object&gt; values = mc.getMulti(keys);
 * </pre> 
 * <h3>To retrieve an multiple objects from the cache with custom hashing</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	String[] keys      = { "key", "key1", "key2" };
 *	Integer[] hashes   = { new Integer(45), new Integer(32), new Integer(44) };
 *	Map&lt;Object&gt; values = mc.getMulti(keys, hashes);
 * </pre> 
 * <h3>To flush all items in server(s)</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	mc.flushAll();
 * </pre> 
 * <h3>To get stats from server(s)</h3>
 * <pre>
 *	MemCachedClient mc = new MemCachedClient();
 *	Map stats = mc.stats();
 * </pre> 
 *
 * @author greg whalin <greg@meetup.com> 
 * @author Richard 'toast' Russo <russor@msoe.edu>
 * @author Kevin Burton <burton@peerfear.org>
 * @author Robert Watts <robert@wattsit.co.uk>
 * @author Vin Chawla <vin@tivo.com>
 * @version 1.5
 */
@SuppressWarnings("unchecked")
public class MemCachedClient {

	// return codes
	private static final String VALUE        = "VALUE";			
	private static final String STATS        = "STAT";			
	private static final String ITEM         = "ITEM";			
	private static final String DELETED      = "DELETED";		
	private static final String NOTFOUND     = "NOT_FOUND";		
	private static final String STORED       = "STORED";		
	private static final String NOTSTORED    = "NOT_STORED";	
	private static final String OK           = "OK";			
	private static final String END          = "END";			
	private static final String ERROR        = "ERROR";			
	private static final String CLIENT_ERROR = "CLIENT_ERROR";	
	private static final String SERVER_ERROR = "SERVER_ERROR";	

	private static final int COMPRESS_THRESH = 30720;
    
	private static final int F_COMPRESSED = 2;
	private static final int F_SERIALIZED = 8;
	
	private boolean primitiveAsString;
	private boolean compressEnable;
	private long compressThreshold;
	private String defaultEncoding;

	private String poolName;

	private ClassLoader classLoader;

	/**
	 * Creates a new instance of MemCachedClient.
	 */
	public MemCachedClient() {
		init();
	}

	/** 
	 * Creates a new instance of MemCacheClient but
	 * acceptes a passed in ClassLoader.
	 * 
	 * @param classLoader ClassLoader object.
	 */
	public MemCachedClient( ClassLoader classLoader ) {
		this.classLoader = classLoader;
		init();
	}

	/** 
	 * Initializes client object to defaults.
	 *
	 * This enables compression and sets compression threshhold to 15 KB.
	 */
	private void init() {
		this.primitiveAsString  = false;
		this.compressEnable     = true;
		this.compressThreshold  = COMPRESS_THRESH;
		this.defaultEncoding    = "UTF-8";
		this.poolName           = "default";
	}

	/** 
	 * Sets the pool that this instance of the client will use.
	 * The pool must already be initialized or none of this will work.
	 * 
	 * @param poolName name of the pool to use
	 */
	public void setPoolName( String poolName ) {
		this.poolName = poolName;
	}

	/** 
	 * Enables storing primitive types as their String values. 
	 * 
	 * @param primitiveAsString if true, then store all primitives as their string value.
	 */
	public void setPrimitiveAsString( boolean primitiveAsString ) {
		this.primitiveAsString = primitiveAsString;
	}

	/** 
	 * Sets default String encoding when storing primitives as Strings. 
	 * Default is UTF-8.
	 * 
	 * @param defaultEncoding 
	 */
	public void setDefaultEncoding( String defaultEncoding ) {
		this.defaultEncoding = defaultEncoding;
	}

	/**
	 * Enable storing compressed data, provided it meets the threshold requirements.
	 *
	 * If enabled, data will be stored in compressed form if it is<br/>
	 * longer than the threshold length set with setCompressThreshold(int)<br/>
	 *<br/>
	 * The default is that compression is enabled.<br/>
	 *<br/>
	 * Even if compression is disabled, compressed data will be automatically<br/>
	 * decompressed.
	 *
	 * @param compressEnable <CODE>true</CODE> to enable compression, <CODE>false</CODE> to disable compression
	 */
	public void setCompressEnable( boolean compressEnable ) {
		this.compressEnable = compressEnable;
	}
    
	/**
	 * Sets the required length for data to be considered for compression.
	 *
	 * If the length of the data to be stored is not equal or larger than this value, it will
	 * not be compressed.
	 *
	 * This defaults to 15 KB.
	 *
	 * @param compressThreshold required length of data to consider compression
	 */
	public void setCompressThreshold( long compressThreshold ) {
		this.compressThreshold = compressThreshold;
	}

	/** 
	 * Checks to see if key exists in cache. 
	 * 
	 * @param key the key to look for
	 * @return true if key found in cache, false if not (or if cache is down)
	 */
	public boolean keyExists( String key ) {
		return ( this.get( key, null, true ) != null );
	}
	
	/**
	 * Deletes an object from cache given cache key.
	 *
	 * @param key the key to be removed
	 * @return <code>true</code>, if the data was deleted successfully
	 */
	public boolean delete( String key ) {
		return delete( key, null, null );
	}

	/** 
	 * Deletes an object from cache given cache key and expiration date. 
	 * 
	 * @param key the key to be removed
	 * @param expiry when to expire the record.
	 * @return <code>true</code>, if the data was deleted successfully
	 */
	public boolean delete( String key, Date expiry ) {
		return delete( key, null, expiry );
	}

	/**
	 * Deletes an object from cache given cache key, a delete time, and an optional hashcode.
	 *
	 *  The item is immediately made non retrievable.<br/>
	 *  Keep in mind {@link #add(String, Object) add} and {@link #replace(String, Object) replace}<br/>
	 *  will fail when used with the same key will fail, until the server reaches the<br/>
	 *  specified time. However, {@link #set(String, Object) set} will succeed,<br/>
	 *  and the new value will not be deleted.
	 *
	 * @param key the key to be removed
	 * @param hashCode if not null, then the int hashcode to use
	 * @param expiry when to expire the record.
	 * @return <code>true</code>, if the data was deleted successfully
	 */
	public boolean delete( String key, Integer hashCode, Date expiry ) {

		if ( key == null ) {
			return false;
		}

		try {
			key = sanitizeKey( key );
		}
		catch ( UnsupportedEncodingException e ) {
			return false;
		}

		SockIOPool.SockIO sock = SockIOPool.getInstance( poolName ).getSock( key, hashCode );

		if ( sock == null )
			return false;

		StringBuffer command = new StringBuffer( "delete " ).append( key );
		if ( expiry != null )
			command.append( " " + expiry.getTime() / 1000 );

		command.append( "\r\n" );
		
		try {
			sock.write( command.toString().getBytes() );
			sock.flush();
			
			String line = sock.readLine();
			if ( DELETED.equals( line ) ) {
				sock.close();
				sock = null;
				return true;
			}
			else if ( NOTFOUND.equals( line ) ) {
			}
			else {
			}
		}
		catch ( IOException e ) {

			try {
				sock.trueClose();
			}
			catch ( IOException ioe ) {
			}

			sock = null;
		}

		if ( sock != null )
			sock.close();

		return false;
	}
    
	/**
	 * Stores data on the server; only the key and the value are specified.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @return true, if the data was successfully stored
	 */
	public boolean set( String key, Object value ) {
		return set( "set", key, value, null, null, primitiveAsString );
	}

	/**
	 * Stores data on the server; only the key and the value are specified.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @param hashCode if not null, then the int hashcode to use
	 * @return true, if the data was successfully stored
	 */
	public boolean set( String key, Object value, Integer hashCode ) {
		return set( "set", key, value, null, hashCode, primitiveAsString );
	}

	/**
	 * Stores data on the server; the key, value, and an expiration time are specified.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @param expiry when to expire the record
	 * @return true, if the data was successfully stored
	 */
	public boolean set( String key, Object value, Date expiry ) {
		return set( "set", key, value, expiry, null, primitiveAsString );
	}

	/**
	 * Stores data on the server; the key, value, and an expiration time are specified.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @param expiry when to expire the record
	 * @param hashCode if not null, then the int hashcode to use
	 * @return true, if the data was successfully stored
	 */
	public boolean set( String key, Object value, Date expiry, Integer hashCode ) {
		return set( "set", key, value, expiry, hashCode, primitiveAsString );
	}

	/**
	 * Adds data to the server; only the key and the value are specified.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @return true, if the data was successfully stored
	 */
	public boolean add( String key, Object value ) {
		return set( "add", key, value, null, null, primitiveAsString );
	}

	/**
	 * Adds data to the server; the key, value, and an optional hashcode are passed in.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @param hashCode if not null, then the int hashcode to use
	 * @return true, if the data was successfully stored
	 */
	public boolean add( String key, Object value, Integer hashCode ) {
		return set( "add", key, value, null, hashCode, primitiveAsString );
	}

	/**
	 * Adds data to the server; the key, value, and an expiration time are specified.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @param expiry when to expire the record
	 * @return true, if the data was successfully stored
	 */
	public boolean add( String key, Object value, Date expiry ) {
		return set( "add", key, value, expiry, null, primitiveAsString );
	}

	/**
	 * Adds data to the server; the key, value, and an expiration time are specified.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @param expiry when to expire the record
	 * @param hashCode if not null, then the int hashcode to use
	 * @return true, if the data was successfully stored
	 */
	public boolean add( String key, Object value, Date expiry, Integer hashCode ) {
		return set( "add", key, value, expiry, hashCode, primitiveAsString );
	}

	/**
	 * Updates data on the server; only the key and the value are specified.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @return true, if the data was successfully stored
	 */
	public boolean replace( String key, Object value ) {
		return set( "replace", key, value, null, null, primitiveAsString );
	}

	/**
	 * Updates data on the server; only the key and the value and an optional hash are specified.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @param hashCode if not null, then the int hashcode to use
	 * @return true, if the data was successfully stored
	 */
	public boolean replace( String key, Object value, Integer hashCode ) {
		return set( "replace", key, value, null, hashCode, primitiveAsString );
	}

	/**
	 * Updates data on the server; the key, value, and an expiration time are specified.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @param expiry when to expire the record
	 * @return true, if the data was successfully stored
	 */
	public boolean replace( String key, Object value, Date expiry ) {
		return set( "replace", key, value, expiry, null, primitiveAsString );
	}

	/**
	 * Updates data on the server; the key, value, and an expiration time are specified.
	 *
	 * @param key key to store data under
	 * @param value value to store
	 * @param expiry when to expire the record
	 * @param hashCode if not null, then the int hashcode to use
	 * @return true, if the data was successfully stored
	 */
	public boolean replace( String key, Object value, Date expiry, Integer hashCode ) {
		return set( "replace", key, value, expiry, hashCode, primitiveAsString );
	}

	/** 
	 * Stores data to cache.
	 *
	 * If data does not already exist for this key on the server, or if the key is being<br/>
	 * deleted, the specified value will not be stored.<br/>
	 * The server will automatically delete the value when the expiration time has been reached.<br/>
	 * <br/>
	 * If compression is enabled, and the data is longer than the compression threshold<br/>
	 * the data will be stored in compressed form.<br/>
	 * <br/>
	 * As of the current release, all objects stored will use java serialization.
	 * 
	 * @param cmdname action to take (set, add, replace)
	 * @param key key to store cache under
	 * @param value object to cache
	 * @param expiry expiration
	 * @param hashCode if not null, then the int hashcode to use
	 * @param asString store this object as a string?
	 * @return true/false indicating success
	 */
	private boolean set( String cmdname, String key, Object value, Date expiry, Integer hashCode, boolean asString ) {

		if ( cmdname == null || cmdname.trim().equals( "" ) || key == null ) {
			return false;
		}

		try {
			key = sanitizeKey( key );
		}
		catch ( UnsupportedEncodingException e ) {
			return false;
		}

		if ( value == null ) {
			return false;
		}

		SockIOPool.SockIO sock = SockIOPool.getInstance(poolName).getSock( key, hashCode );
		
		if ( sock == null )
			return false;
		
		if ( expiry == null )
			expiry = new Date(0);

		int flags = 0;
		
		byte[] val;

        if ( NativeHandler.isHandled( value ) ) {
			
			if ( asString ) {
				try {
					val = value.toString().getBytes( defaultEncoding );
				}
				catch ( UnsupportedEncodingException ue ) {
					sock.close();
					sock = null;
					return false;
				}
			}
			else {
				try {
					val = NativeHandler.encode( value );
				}
				catch ( Exception e ) {

					sock.close();
					sock = null;
					return false;
				}
			}
		}
		else {
			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				(new ObjectOutputStream( bos )).writeObject( value );
				val = bos.toByteArray();
				flags |= F_SERIALIZED;
			}
			catch ( IOException e ) {
				sock.close();
				sock = null;
				return false;
			}
		}
		
		if ( compressEnable && val.length > compressThreshold ) {

			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream( val.length );
				GZIPOutputStream gos = new GZIPOutputStream( bos );
				gos.write( val, 0, val.length );
				gos.finish();
				
				val = bos.toByteArray();
				flags |= F_COMPRESSED;

			}
			catch (IOException e) {
			}
		}

		try {
			String cmd = cmdname + " " + key + " " + flags + " "
				+ expiry.getTime() / 1000 + " " + val.length + "\r\n";
			sock.write( cmd.getBytes() );
			sock.write( val );
			sock.write( "\r\n".getBytes() );
			sock.flush();

			String line = sock.readLine();

			if ( STORED.equals( line ) ) {
				sock.close();
				sock = null;
				return true;
			}
			else if ( NOTSTORED.equals( line ) ) {
			}
			else {
			}
		}
		catch ( IOException e ) {

			try {
				sock.trueClose();
			}
			catch ( IOException ioe ) {
			}

			sock = null;
		}

		if ( sock != null )
			sock.close();

		return false;
	}

	/** 
	 * Store a counter to memcached given a key
	 * 
	 * @param key cache key
	 * @param counter number to store
	 * @return true/false indicating success
	 */
	public boolean storeCounter( String key, long counter ) {
		return set( "set", key, new Long( counter ), null, null, true );
	}

	/** 
	 * Store a counter to memcached given a key
	 * 
	 * @param key cache key
	 * @param counter number to store
	 * @return true/false indicating success
	 */
	public boolean storeCounter( String key, Long counter ) {
		return set( "set", key, counter, null, null, true );
	}
    
	/** 
	 * Store a counter to memcached given a key
	 * 
	 * @param key cache key
	 * @param counter number to store
	 * @param hashCode if not null, then the int hashcode to use
	 * @return true/false indicating success
	 */
	public boolean storeCounter( String key, Long counter, Integer hashCode ) {
		return set( "set", key, counter, null, hashCode, true );
	}

	/** 
	 * Returns value in counter at given key as long. 
	 *
	 * @param key cache ket
	 * @return counter value or -1 if not found
	 */
	public long getCounter( String key ) {
		return getCounter( key, null );
	}

	/** 
	 * Returns value in counter at given key as long. 
	 *
	 * @param key cache ket
	 * @param hashCode if not null, then the int hashcode to use
	 * @return counter value or -1 if not found
	 */
	public long getCounter( String key, Integer hashCode ) {

		if ( key == null ) {
			return -1;
		}

		try {
			key = sanitizeKey( key );
		}
		catch ( UnsupportedEncodingException e ) {
			return -1;
		}

		long counter = -1;
		try {
			counter = Long.parseLong( (String)get( key, hashCode, true ) );
		}
		catch ( Exception ex ) {
		}
		
		return counter;
	}

	/**
	 * Increment the value at the specified key by 1, and then return it.
	 *
	 * @param key key where the data is stored
	 * @return -1, if the key is not found, the value after incrementing otherwise
	 */
	public long incr( String key ) {
		return incrdecr( "incr", key, 1, null );
	}

	/** 
	 * Increment the value at the specified key by passed in val. 
	 * 
	 * @param key key where the data is stored
	 * @param inc how much to increment by
	 * @return -1, if the key is not found, the value after incrementing otherwise
	 */
	public long incr( String key, long inc ) {
		return incrdecr( "incr", key, inc, null );
	}

	/**
	 * Increment the value at the specified key by the specified increment, and then return it.
	 *
	 * @param key key where the data is stored
	 * @param inc how much to increment by
	 * @param hashCode if not null, then the int hashcode to use
	 * @return -1, if the key is not found, the value after incrementing otherwise
	 */
	public long incr( String key, long inc, Integer hashCode ) {
		return incrdecr( "incr", key, inc, hashCode );
	}
	
	/**
	 * Decrement the value at the specified key by 1, and then return it.
	 *
	 * @param key key where the data is stored
	 * @return -1, if the key is not found, the value after incrementing otherwise
	 */
	public long decr( String key ) {
		return incrdecr( "decr", key, 1, null );
	}

	/**
	 * Decrement the value at the specified key by passed in value, and then return it.
	 *
	 * @param key key where the data is stored
	 * @param inc how much to increment by
	 * @return -1, if the key is not found, the value after incrementing otherwise
	 */
	public long decr( String key, long inc ) {
		return incrdecr( "decr", key, inc, null );
	}

	/**
	 * Decrement the value at the specified key by the specified increment, and then return it.
	 *
	 * @param key key where the data is stored
	 * @param inc how much to increment by
	 * @param hashCode if not null, then the int hashcode to use
	 * @return -1, if the key is not found, the value after incrementing otherwise
	 */
	public long decr( String key, long inc, Integer hashCode ) {
		return incrdecr( "decr", key, inc, hashCode );
	}

	/** 
	 * Increments/decrements the value at the specified key by inc.
	 * 
	 *  Note that the server uses a 32-bit unsigned integer, and checks for<br/>
	 *  underflow. In the event of underflow, the result will be zero.  Because<br/>
	 *  Java lacks unsigned types, the value is returned as a 64-bit integer.<br/>
	 *  The server will only decrement a value if it already exists;<br/>
	 *  if a value is not found, -1 will be returned.
	 *
	 * @param cmdname increment/decrement
	 * @param key cache key
	 * @param inc amount to incr or decr
	 * @param hashCode if not null, then the int hashcode to use
	 * @return new value or -1 if not exist
	 */
	private long incrdecr( String cmdname, String key, long inc, Integer hashCode ) {

		if ( key == null ) {
			return -1;
		}

		try {
			key = sanitizeKey( key );
		}
		catch ( UnsupportedEncodingException e ) {
			return -1;
		}

		SockIOPool.SockIO sock = SockIOPool.getInstance( poolName ).getSock(key, hashCode);

		if (sock == null)
			return -1;
		
		try {
			String cmd = cmdname + " " + key + " " + inc + "\r\n";

			sock.write( cmd.getBytes() );
			sock.flush();

			String line = sock.readLine();

			if ( line.matches( "\\d+" ) ) {

				sock.close();
				try {
					return Long.parseLong( line );
				}
				catch ( Exception ex ) {
				}
 			}
			else if ( NOTFOUND.equals( line ) ) {
			}
			else {
			}
		}
		catch ( IOException e ) {

			try {
				sock.trueClose();
			}
			catch ( IOException ioe ) {
			}

			sock = null;
		}
		
		if ( sock != null )
			sock.close();

		return -1;
	}

	/**
	 * Retrieve a key from the server, using a specific hash.
	 *
	 *  If the data was compressed or serialized when compressed, it will automatically<br/>
	 *  be decompressed or serialized, as appropriate. (Inclusive or)<br/>
	 *<br/>
	 *  Non-serialized data will be returned as a string, so explicit conversion to<br/>
	 *  numeric types will be necessary, if desired<br/>
	 *
	 * @param key key where data is stored
	 * @return the object that was previously stored, or null if it was not previously stored
	 */
	public Object get( String key ) {
		return get( key, null, false );
	}

	/** 
	 * Retrieve a key from the server, using a specific hash.
	 *
	 *  If the data was compressed or serialized when compressed, it will automatically<br/>
	 *  be decompressed or serialized, as appropriate. (Inclusive or)<br/>
	 *<br/>
	 *  Non-serialized data will be returned as a string, so explicit conversion to<br/>
	 *  numeric types will be necessary, if desired<br/>
	 *
	 * @param key key where data is stored
	 * @param hashCode if not null, then the int hashcode to use
	 * @return the object that was previously stored, or null if it was not previously stored
	 */
	public Object get( String key, Integer hashCode ) {
		return get( key, hashCode, false );
	}

	/**
	 * Retrieve a key from the server, using a specific hash.
	 *
	 *  If the data was compressed or serialized when compressed, it will automatically<br/>
	 *  be decompressed or serialized, as appropriate. (Inclusive or)<br/>
	 *<br/>
	 *  Non-serialized data will be returned as a string, so explicit conversion to<br/>
	 *  numeric types will be necessary, if desired<br/>
	 *
	 * @param key key where data is stored
	 * @param hashCode if not null, then the int hashcode to use
	 * @param asString if true, then return string val
	 * @return the object that was previously stored, or null if it was not previously stored
	 */
	public Object get( String key, Integer hashCode, boolean asString ) {

		if ( key == null ) {
			return null;
		}

		try {
			key = sanitizeKey( key );
		}
		catch ( UnsupportedEncodingException e ) {
			return false;
		}

		SockIOPool.SockIO sock = SockIOPool.getInstance( poolName ).getSock( key, hashCode );
	    
	    if ( sock == null )
			return null;

	    try {
			String cmd = "get " + key + "\r\n";

			sock.write( cmd.getBytes() );
			sock.flush();
			Map<String,Object> hm =
				new HashMap<String,Object>();
			loadItems( sock, hm, asString );
			sock.close();
			return hm.get( key );
	    }
		catch ( IOException e ) {

			try {
				sock.trueClose();
			}
			catch ( IOException ioe ) {
			}
			sock = null;
	    }

		if ( sock != null )
			sock.close();

		return null;
	}

	/** 
	 * Retrieve multiple objects from the memcache.
	 *
	 *  This is recommended over repeated calls to {@link #get(String) get()}, since it<br/>
	 *  is more efficient.<br/>
	 *
	 * @param keys String array of keys to retrieve
	 * @return Object array ordered in same order as key array containing results
	 */
	public Object[] getMultiArray( String[] keys ) {
		return getMultiArray( keys, null, false );
	}

	/** 
	 * Retrieve multiple objects from the memcache.
	 *
	 *  This is recommended over repeated calls to {@link #get(String) get()}, since it<br/>
	 *  is more efficient.<br/>
	 *
	 * @param keys String array of keys to retrieve
	 * @param hashCodes if not null, then the Integer array of hashCodes
	 * @return Object array ordered in same order as key array containing results
	 */
	public Object[] getMultiArray( String[] keys, Integer[] hashCodes ) {
		return getMultiArray( keys, hashCodes, false );
	}

	/** 
	 * Retrieve multiple objects from the memcache.
	 *
	 *  This is recommended over repeated calls to {@link #get(String) get()}, since it<br/>
	 *  is more efficient.<br/>
	 *
	 * @param keys String array of keys to retrieve
	 * @param hashCodes if not null, then the Integer array of hashCodes
	 * @param asString if true, retrieve string vals
	 * @return Object array ordered in same order as key array containing results
	 */
	public Object[] getMultiArray( String[] keys, Integer[] hashCodes, boolean asString ) {

		Map<String,Object> data = getMulti( keys, hashCodes, asString );

		if ( data == null )
			return null;

		Object[] res = new Object[ keys.length ];
		for ( int i = 0; i < keys.length; i++ ) {
			res[i] = data.get( keys[i] );
		}

		return res;
	}

	/**
	 * Retrieve multiple objects from the memcache.
	 *
	 *  This is recommended over repeated calls to {@link #get(String) get()}, since it<br/>
	 *  is more efficient.<br/>
	 *
	 * @param keys String array of keys to retrieve
	 * @return a hashmap with entries for each key is found by the server,
	 *      keys that are not found are not entered into the hashmap, but attempting to
	 *      retrieve them from the hashmap gives you null.
	 */
	public Map<String,Object> getMulti( String[] keys ) {
		return getMulti( keys, null, false );
	}
    
	/**
	 * Retrieve multiple keys from the memcache.
	 *
	 *  This is recommended over repeated calls to {@link #get(String) get()}, since it<br/>
	 *  is more efficient.<br/>
	 *
	 * @param keys keys to retrieve
	 * @param hashCodes if not null, then the Integer array of hashCodes
	 * @return a hashmap with entries for each key is found by the server,
	 *      keys that are not found are not entered into the hashmap, but attempting to
	 *      retrieve them from the hashmap gives you null.
	 */
	public Map<String,Object> getMulti( String[] keys, Integer[] hashCodes ) {
		return getMulti( keys, hashCodes, false );
	}

	/**
	 * Retrieve multiple keys from the memcache.
	 *
	 *  This is recommended over repeated calls to {@link #get(String) get()}, since it<br/>
	 *  is more efficient.<br/>
	 *
	 * @param keys keys to retrieve
	 * @param hashCodes if not null, then the Integer array of hashCodes
	 * @param asString if true then retrieve using String val
	 * @return a hashmap with entries for each key is found by the server,
	 *      keys that are not found are not entered into the hashmap, but attempting to
	 *      retrieve them from the hashmap gives you null.
	 */
	public Map<String,Object> getMulti( String[] keys, Integer[] hashCodes, boolean asString ) {

		if ( keys == null || keys.length == 0 ) {
			return null;
		}

		Map<String,StringBuilder> sockKeys =
			new HashMap<String,StringBuilder>();

		for ( int i = 0; i < keys.length; ++i ) {

			Integer hash = null;
			if ( hashCodes != null && hashCodes.length > i )
				hash = hashCodes[ i ];

			String key = keys[i];

			if ( key == null ) {
				continue;
			}

			try {
				key = sanitizeKey( key );
			}
			catch ( UnsupportedEncodingException e ) {
				continue;
			}

			SockIOPool.SockIO sock = SockIOPool.getInstance( poolName ).getSock( key, hash );

			if ( sock == null )
				continue;

			if ( !sockKeys.containsKey( sock.getHost() ) )
				sockKeys.put( sock.getHost(), new StringBuilder() );

			sockKeys.get( sock.getHost() ).append( " " + key );

			sock.close();
		}
		Map<String,Object> ret =
			new HashMap<String,Object>( keys.length );

		for ( Iterator<String> i = sockKeys.keySet().iterator(); i.hasNext(); ) {
			String host = i.next();
			SockIOPool.SockIO sock = SockIOPool.getInstance( poolName ).getConnection( host );

			try {
				String cmd = "get" + sockKeys.get( host ) + "\r\n";
				sock.write( cmd.getBytes() );
				sock.flush();
				loadItems( sock, ret, asString );
			}
			catch ( IOException e ) {
				i.remove();
				try {
					sock.trueClose();
				}
				catch ( IOException ioe ) {
				}
				sock = null;
			}

			if ( sock != null )
				sock.close();
		}
		
		return ret;
	}
    
	/** 
	 * This method loads the data from cache into a Map.
	 *
	 * Pass a SockIO object which is ready to receive data and a HashMap<br/>
	 * to store the results.
	 * 
	 * @param sock socket waiting to pass back data
	 * @param hm hashmap to store data into
	 * @param asString if true, and if we are using NativehHandler, return string val
	 * @throws IOException if io exception happens while reading from socket
	 */
	private void loadItems( SockIOPool.SockIO sock, Map<String,Object> hm, boolean asString ) throws IOException {

		while ( true ) {
			String line = sock.readLine();

			if ( line.startsWith( VALUE ) ) {
				String[] info = line.split(" ");
				String key    = info[1];
				int flag      = Integer.parseInt( info[2] );
				int length    = Integer.parseInt( info[3] );

				byte[] buf = new byte[length];
				sock.read( buf );
				sock.clearEOL();

				Object o;
				
				if ( (flag & F_COMPRESSED) != 0 ) {
					try {
						GZIPInputStream gzi = new GZIPInputStream( new ByteArrayInputStream( buf ) );
						ByteArrayOutputStream bos = new ByteArrayOutputStream( buf.length );
						
						int count;
						byte[] tmp = new byte[2048];
						while ( (count = gzi.read(tmp)) != -1 ) {
							bos.write( tmp, 0, count );
						}

						buf = bos.toByteArray();
						gzi.close();
					}
					catch ( IOException e ) {
						throw new NestedIOException( "++++ IOException thrown while trying to uncompress input stream for key: " + key, e );
					}
				}

				if ( (flag & F_SERIALIZED) == 0 ) {
					if ( primitiveAsString || asString ) {
						o = new String( buf, defaultEncoding );
					}
					else {
						try {
							o = NativeHandler.decode( buf );    
						}
						catch ( Exception e ) {
							throw new NestedIOException( e );
						}
					}
				}
				else {
					ContextObjectInputStream ois =
						new ContextObjectInputStream( new ByteArrayInputStream( buf ), classLoader );
					try {
						o = ois.readObject();
					}
					catch ( ClassNotFoundException e ) {
						throw new NestedIOException( "+++ failed while trying to deserialize for key: " + key, e );
					}
				}

				hm.put( key, o );
			}
			else if ( END.equals( line ) ) {
				break;
			}
		}
	}

	private String sanitizeKey( String key ) throws UnsupportedEncodingException {
		return URLEncoder.encode( key, "UTF-8" );
	}

	/** 
	 * Invalidates the entire cache.
	 *
	 * Will return true only if succeeds in clearing all servers.
	 * 
	 * @return success true/false
	 */
	public boolean flushAll() {
		return flushAll( null );
	}

	/** 
	 * Invalidates the entire cache.
	 *
	 * Will return true only if succeeds in clearing all servers.
	 * If pass in null, then will try to flush all servers.
	 * 
	 * @param servers optional array of host(s) to flush (host:port)
	 * @return success true/false
	 */
	public boolean flushAll( String[] servers ) {

		SockIOPool pool = SockIOPool.getInstance( poolName );

		if ( pool == null ) {
			return false;
		}

		servers = ( servers == null )
			? pool.getServers()
			: servers;

		if ( servers == null || servers.length <= 0 ) {
			return false;
		}

		boolean success = true;

		for ( int i = 0; i < servers.length; i++ ) {

			SockIOPool.SockIO sock = pool.getConnection( servers[i] );
			if ( sock == null ) {
				success = false;
				continue;
			}

			String command = "flush_all\r\n";

			try {
				sock.write( command.getBytes() );
				sock.flush();

				String line = sock.readLine();
				success = ( OK.equals( line ) )
					? success && true
					: false;
			}
			catch ( IOException e ) {

				try {
					sock.trueClose();
				}
				catch ( IOException ioe ) {
				}

				success = false;
				sock = null;
			}

			if ( sock != null )
				sock.close();
		}

		return success;
	}

	/** 
	 * Retrieves stats for all servers.
	 *
	 * Returns a map keyed on the servername.
	 * The value is another map which contains stats
	 * with stat name as key and value as value.
	 * 
	 * @return Stats map
	 */
	public Map stats() {
		return stats( null );
	}

	/** 
	 * Retrieves stats for passed in servers (or all servers).
	 *
	 * Returns a map keyed on the servername.
	 * The value is another map which contains stats
	 * with stat name as key and value as value.
	 * 
	 * @param servers string array of servers to retrieve stats from, or all if this is null	 
	 * @return Stats map
	 */
	public Map stats( String[] servers ) {
		return stats( servers, "stats\r\n", STATS );
	}	

	/** 
	 * Retrieves stats items for all servers.
	 *
	 * Returns a map keyed on the servername.
	 * The value is another map which contains item stats
	 * with itemname:number:field as key and value as value.
	 * 
	 * @return Stats map
	 */
	public Map statsItems() {
		return statsItems( null );
	}
	
	/** 
	 * Retrieves stats for passed in servers (or all servers).
	 *
	 * Returns a map keyed on the servername.
	 * The value is another map which contains item stats
	 * with itemname:number:field as key and value as value.
	 * 
	 * @param servers string array of servers to retrieve stats from, or all if this is null
	 * @return Stats map
	 */
	public Map statsItems( String[] servers ) {
		return stats( servers, "stats items\r\n", STATS );
	}
	
	/** 
	 * Retrieves stats items for all servers.
	 *
	 * Returns a map keyed on the servername.
	 * The value is another map which contains slabs stats
	 * with slabnumber:field as key and value as value.
	 * 
	 * @return Stats map
	 */
	public Map statsSlabs() {
		return statsSlabs( null );
	}
	
	/** 
	 * Retrieves stats for passed in servers (or all servers).
	 *
	 * Returns a map keyed on the servername.
	 * The value is another map which contains slabs stats
	 * with slabnumber:field as key and value as value.
	 * 
	 * @param servers string array of servers to retrieve stats from, or all if this is null
	 * @return Stats map
	 */
	public Map statsSlabs( String[] servers ) {
		return stats( servers, "stats slabs\r\n", STATS );
	}
	
	/** 
	 * Retrieves items cachedump for all servers.
	 *
	 * Returns a map keyed on the servername.
	 * The value is another map which contains cachedump stats
	 * with the cachekey as key and byte size and unix timestamp as value.
	 * 
	 * @param slabNumber the item number of the cache dump
	 * @return Stats map
	 */
	public Map statsCacheDump( int slabNumber ) {
		return statsCacheDump( null, slabNumber );
	}
	
	/** 
	 * Retrieves stats for passed in servers (or all servers).
	 *
	 * Returns a map keyed on the servername.
	 * The value is another map which contains cachedump stats
	 * with the cachekey as key and byte size and unix timestamp as value.
	 * 
	 * @param servers string array of servers to retrieve stats from, or all if this is null
	 * @param slabNumber the item number of the cache dump
	 * @return Stats map
	 */
	public Map statsCacheDump( String[] servers, int slabNumber ) {
		return stats( servers, "stats cachedump " + String.valueOf( slabNumber ) + "\r\n", ITEM );
	}
		
	private Map stats( String[] servers, String command, String lineStart ) {

		if ( command == null || command.trim().equals( "" ) ) {
			return null;
		}

		SockIOPool pool = SockIOPool.getInstance( poolName );

		if ( pool == null ) {
			return null;
		}

		servers = (servers == null)
			? pool.getServers()
			: servers;

		if ( servers == null || servers.length <= 0 ) {
			return null;
		}

		Map<String,Map> statsMaps =
			new HashMap<String,Map>();

		for ( int i = 0; i < servers.length; i++ ) {

			SockIOPool.SockIO sock = pool.getConnection( servers[i] );
			if ( sock == null ) {
				continue;
			}

			try {
				sock.write( command.getBytes() );
				sock.flush();

				Map stats = new HashMap();

				while ( true ) {
					String line = sock.readLine();

					if ( line.startsWith( lineStart ) ) {
						String[] info = line.split( " ", 3 );						
						String key    = info[1];
						String value  = info[2];
						stats.put( key, value );
					}
					else if ( END.equals( line ) ) {
						break;
					}

					statsMaps.put( servers[i], stats );
				}
			}
			catch ( IOException e ) {

				try {
					sock.trueClose();
				}
				catch ( IOException ioe ) {
				}

				sock = null;
			}

			if ( sock != null )
				sock.close();
		}

		return statsMaps;
	}
}
