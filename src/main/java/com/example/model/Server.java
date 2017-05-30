//package com.example.model;
//
//import javax.persistence.*;
//
//@Entity
//public class Server {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	Long id;
//
//	private String host;
//
//	private String port;
//
//	private String name;
//	
//	private serverType type;
//
//	private enum serverType {
//		SERVEURAPPLICATIF, SERVEURWEB, SERVEURBD, SERVEURSMTP
//	};
//
//	/**
//	 * 
//	 */
//	public Server() {
//		super();
//	}
//
//	public Server(String host, String name) {
//		super();
//		this.host = host;
//		this.name = name;
//	}
//
//	public String getHost() {
//		return host;
//	}
//
//	/**
//	 * @return the port
//	 */
//	public String getPort() {
//		return port;
//	}
//
//	/**
//	 * @param port
//	 *            the port to set
//	 */
//	public void setPort(String port) {
//		this.port = port;
//	}
//
//	/**
//	 * @return the id
//	 */
//	public Long getId() {
//		return id;
//	}
//
//	public void setHost(String host) {
//		this.host = host;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	/**
//	 * @return the type
//	 */
//	public serverType getType() {
//		return type;
//	}
//
//	/**
//	 * @param type the type to set
//	 */
//	public void setType(serverType type) {
//		this.type = type;
//	}
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#hashCode()
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((host == null) ? 0 : host.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((port == null) ? 0 : port.hashCode());
//		result = prime * result + ((type == null) ? 0 : type.hashCode());
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Server other = (Server) obj;
//		if (host == null) {
//			if (other.host != null)
//				return false;
//		} else if (!host.equals(other.host))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		if (port == null) {
//			if (other.port != null)
//				return false;
//		} else if (!port.equals(other.port))
//			return false;
//		if (type != other.type)
//			return false;
//		return true;
//	}
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "Server [id=" + id + ", host=" + host + ", port=" + port + ", name=" + name + ", type=" + type + "]";
//	}
//	
//	
//}
