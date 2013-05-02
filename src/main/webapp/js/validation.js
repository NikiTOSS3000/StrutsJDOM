var background_norm = "white";
var background_fail = "#fcc";
var background_gray = "#eee";

function validateExists(e) {
    if (e.value == "") {
        e.style.background = background_fail;
        return false;
    } else {
        e.style.background = background_norm;
        return true;
    }
}

function validateCheckBox(e) {
    id = e.value;
    price = document.getElementById('price_' + id);
    if (price != null) {
        if (e.checked) {
            price.disabled = false;
            price.style.background = background_norm;
        } else {
            price.value = "";
            price.disabled = true;
            price.style.background = background_gray;
        }
    } else {
        price = document.getElementById('price');
        if (e.checked) {
            price.disabled = false;
            price.style.background = background_norm;
        } else {
            price.value = "";
            price.disabled = true;
            price.style.background = background_gray;
        }
    }
}

function validateModel(e) {
    var pattern = /^[a-zA-Z]{2}\d{3}$/;
    if (e.value.match(pattern)) {
        e.style.background = background_norm;
        return true;
    } else {
        e.style.background = background_fail;
        return false;
    }
}

function validateDate(e) {
    var pattern = /^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-[12][0-9][0-9][0-9]$/;
    if (e.value.match(pattern)) {
        e.style.background = background_norm;
        return true;
    } else {
        e.style.background = background_fail;
        return false;
    }
}

function validatePrice(e) {
    price = e.value;
    if (!isNaN(price)) {
        if (price > 0) {
            e.style.background = background_norm;
            return true;
        }
    }
    e.style.background = background_fail;
    return false;
}

function validateUpdate() {
    isValid = true;
    for (i = 0; i <= size; i++) {
        isNameValid = validateExists(document.getElementById('name_' + i));
        isProducerValid = validateExists(document.getElementById('producer_' + i));
        isModelValid = validateModel(document.getElementById('model_' + i));
        isDateValid = validateDate(document.getElementById('date_' + i));
        isColorValid = validateExists(document.getElementById('color_' + i));
        isValid = isValid && isNameValid && isProducerValid && isModelValid && isDateValid && isColorValid;
        inStock = document.getElementById('inStock_' + i);
        if (inStock.checked) {
            isPriceValid = validatePrice(document.getElementById('price_' + i));
            isValid = isValid && isPriceValid;

        }
    }
    return isValid;
}
function validateAdd() {
    isValid = true;
    isNameValid = validateExists(document.getElementById('name'));
    isProducerValid = validateExists(document.getElementById('producer'));
    isModelValid = validateModel(document.getElementById('model'));
    isDateValid = validateDate(document.getElementById('date'));
    isColorValid = validateExists(document.getElementById('color'));
    isValid = isValid && isNameValid && isProducerValid && isModelValid && isDateValid && isColorValid;
    inStock = document.getElementById('inStock');
    if (inStock.checked) {
        isPriceValid = validatePrice(document.getElementById('price'));
        isValid = isValid && isPriceValid;
    }
    return isValid;
}