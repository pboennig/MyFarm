N_Values = {"corn" : 67, "rice" : 50, "grain" : 110, "soybean" : 15}
H_2_O_Vals = {"corn": 1}
spacing

def main(land, crop, precip):
    fert = land * N_Values[crop]
    water = H_2_O_Vals[crop] * land - precip * land
    

    
print(fert(1.0, "corn"))