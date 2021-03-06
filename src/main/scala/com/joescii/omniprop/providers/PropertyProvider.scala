package com.joescii.omniprop
package providers

/** Base trait for objects which can provide property values */
trait PropertyProvider {
  def get(key:String):Option[String]
}

/** Configuration object for setting up the stack of PropertyProviders */
object PropertyProviders {
  self =>

  private[omniprop] var stack:List[PropertyProvider] = List()

  /** Configures the stack of PropertyProvider for omniprop to utilize. */
  def configure(stack:PropertyProvider*) = {
    if(stack.isEmpty)       throw InvalidConfigurationException("PropertyProviders.configure must be invoked with a non-empty list")
    if(!self.stack.isEmpty) throw InvalidConfigurationException("PropertyProviders.configure must be invoked only once")

    self.stack = stack.toList
  }
}

/** Provides properties from the JVM's System properties object */
object SystemPropertyProvider extends PropertyProvider {
  private val props = new scala.sys.SystemProperties()

  def get(key:String) = props get key
}

object MapPropertyProvider {
  def apply(entry:(String,String)*):MapPropertyProvider = MapPropertyProvider((entry).toMap)
}
/** Provides properties from a Map object.  Primarily for testing purposes */
case class MapPropertyProvider(m:Map[String, String]) extends PropertyProvider {
  def get(key:String) = m get key
}