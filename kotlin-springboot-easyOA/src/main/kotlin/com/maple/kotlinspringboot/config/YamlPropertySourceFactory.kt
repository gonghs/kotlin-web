package com.maple.kotlinspringboot.config

import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.core.env.PropertySource
import java.io.IOException
import org.springframework.core.io.support.EncodedResource
import org.springframework.core.io.support.PropertySourceFactory
import org.springframework.util.StringUtils
import org.springframework.core.io.Resource

/**
 * TODO
 *
 * @author maple
 * @version V1.0
 * @since 2019-01-15 17:02
 */
class YamlPropertySourceFactory : PropertySourceFactory {

    @Throws(IOException::class)
    override fun createPropertySource(name: String?, resource: EncodedResource): PropertySource<*> {
        return if (name != null)
            YamlPropertySourceLoader().load(name, resource.resource)[0]
        else
            YamlPropertySourceLoader().load(
                    getNameForResource(resource.resource), resource.resource)[0]
    }

    private fun getNameForResource(resource: Resource): String {
        var name = resource.getDescription()
        if (!StringUtils.hasText(name)) {
            name = resource::class.java.getSimpleName() + "@" + System.identityHashCode(resource)
        }
        return name
    }
}