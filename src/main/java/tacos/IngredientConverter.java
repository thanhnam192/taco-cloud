package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.data.IngredientRepository;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientConverter(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String source) {

        return   this.ingredientRepository.findById(source).get();
    }
}
