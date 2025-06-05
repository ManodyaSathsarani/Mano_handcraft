package lk.ijse.mano_handcraft.dto;

public class IngredientManagementDto {
    private String ingredient_Id;
    private String ingredient_Name;
    private String  unit;

    public IngredientManagementDto(String ingredient_Id,String ingredient_Name,String unit) {
        this.ingredient_Id = ingredient_Id;
        this.ingredient_Name = ingredient_Name;
        this.unit = unit;
    }

    public String getIngredient_Id() {
        return ingredient_Id;
    }

    public void setIngredient_Id(String ingredient_Id) {
        this.ingredient_Id = ingredient_Id;
    }

    public String getIngredient_Name() {
        return ingredient_Name;
    }

    public void setIngredient_Name(String ingredient_Name) {
        this.ingredient_Name = ingredient_Name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
