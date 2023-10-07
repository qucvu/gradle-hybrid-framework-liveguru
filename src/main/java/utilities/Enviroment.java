package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:enviromentConfig/${env}.properties"})
public interface Enviroment extends Config {
    String endUserUrl();

    String adminUrl();

}
