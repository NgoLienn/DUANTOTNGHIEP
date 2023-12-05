$(function () {
    apiProvince = (provinces) => {
        let districts;

        provinces.forEach(province => {
            $('#province').append(`<option value="${province.name}">${province.name}</option>`);
        });

        $('#province').change(function () {
            $('#district').html('<option value="-1">Chọn quận/huyện</option>');
            $('#town').html('<option value="-1"> Chọn phường/xã </option>');

            let value = $(this).val();

            $.each(provinces, function (index, province) {
                if (province.name === value) {
                    districts = province.districts;
                    $.each(province.districts, function (index, district) {
                        $('#district').append(`<option value="${district.name}">${district.name}</option>`);
                    });
                }
            });
        });

        $('#district').change(function () {
            $('#town').html('<option value="-1"> Chọn phường/xã </option>');
            let value = $(this).val();

            $.each(districts, function (index, district) {
                if (district.name === value) {
                    district.wards.forEach(ward => {
                        $('#town').append(`<option value="${ward.name}">${ward.name}</option>`);
                    });
                }
            });
        });
    }

    let provinces = JSON.parse(data);
    apiProvince(provinces);
});
