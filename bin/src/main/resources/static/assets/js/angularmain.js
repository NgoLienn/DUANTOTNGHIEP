// Route
var app = angular.module('myApp', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'assets/views/pages/trang-chu.html',
            controller: 'courseCtrl'
        })
        .when('/gioi-thieu', {
            templateUrl: 'assets/views/pages/about.html'
        })
        .when('/lap-trinh-web', {
            templateUrl: 'assets/views/pages/course-web.html'
        })
        .when('/hoi-dap', {
            templateUrl: 'assets/views/pages/f&a.html'
        })
        .when('/blog', {
            templateUrl: 'assets/views/pages/blog.html'
        })
        .when('/blog/lap-trinh-la-gi', {
            templateUrl: 'assets/views/pages/blog-single.html'
        })
        .when('/login', {
            templateUrl: 'assets/views/pages/login.html',
            controller: 'accCtrl',
        })
        .when('/lien-he', {
            templateUrl: 'assets/views/pages/contact.html'
        })
});

/**
 * Course controller
 */
app.controller('courseCtrl', function ($scope, $http, $rootScope) {



    $scope.qick = function (e) {
        // Kiểm tra login
        if (localStorage.getItem('Logged')) {
            let data = JSON.parse(localStorage.getItem('Logged'));
            console.log(data);
            window.location.href = "#!quik"
        } else {
            window.location.href = "#!login"
        }
    }

    $scope.subjects = [];
    $http.get('/assets/db/Subjects.js').then(function (res) {
        console.log('Khóa học: ', res);
        $scope.subjects = res.data;
    }, function (req) {
        alert('lỗi');
    });
})

/**
 * End Course Controller
 */


/**
 * Form Validate Login and Register
 */
app.controller('accCtrl', Account);

function Account($scope, $http, $rootScope) {


    // Khởi tạo đối tượng login
    $scope.login = {
        email: '',
        password: '',
    }


    // Khởi tạo đối tượng Register
    $scope.register = {
        username: '',
        email: '',
        password1: '',
        password2: ''
    }


    // Khởi tạo đối tượng Account và lưu vào localStorage
    $scope.account = [
        {
            username: "",
            password: "",
            fullname: "",
            email: "",
            gender: "",
            birthday: "",
            schoolfee: "",
            marks: ""
        }
    ]




    // Lấy dữ liệu từ local strorage
    const data = JSON.parse(localStorage.getItem('rememberAccount'));
    console.log(data);


    // Gán dữ liệu cho lần đăng nhập tiếp theo khi người dùng nhấn ghi nhớ tài khoản
    if (data != null) {
        $scope.isChecked = data.miss;
        $scope.login.username = data.username;
        $scope.login.password = data.password;
    }


    $scope.fieldType = 'password';
    $scope.icon = 'uil-eye-slash';



    // Show password and update icon
    $scope.showPassword = function () {
        if ($scope.fieldType == 'password' && $scope.icon == 'uil-eye-slash') {
            $scope.fieldType = 'text';
            $scope.icon = 'uil-eye';
        } else if ($scope.fieldType == 'text' && $scope.icon == 'uil-eye') {
            $scope.icon = 'uil-eye-slash';
            $scope.fieldType = 'password';
        }
    }

    // checked remember me
    $scope.checked = function () {
        $scope.isChecked = !$scope.isChecked;
    }

    // SignupLinK
    $scope.signUp = function () {
        let signUp = angular.element(document.querySelector('.container__acc'));

        signUp.addClass('active');
    }

    // LoginLink
    $scope.logIn = function () {
        let signUp = angular.element(document.querySelector('.container__acc'));

        signUp.removeClass('active');
    }

    // message Error
    $scope.emailError = '';
    $scope.paswordError = '';




    $scope.dangNhap = function () {
        // Action
        $scope.emailSuccess = false;
        $scope.passwordSuccess = false;

        console.log('Account', $scope.login.email, $scope.login.password);

        // Kiểm tra local 
        if (localStorage.getItem('Account')) {
            $scope.useSucc = false;
            $scope.passSucc = false;

            let local = JSON.parse(localStorage.getItem('Account'));
            // kiểm tra user
            for (let i = 0; i < local.length; i++) {
                console.log(local[i].username);
                if ($scope.login.username === local[i].username) {
                    $scope.useSucc = true;
                    break;
                }
            }

            alert('Hello')


            // kiểm tra tài khoản
            for (let i = 0; i < local.length; i++) {
                console.log(local[i].password);
                if (local[i].username === $scope.login.username && local[i].password === $scope.login.password) {
                    $scope.passSucc = true;
                    break;
                }
            }

            if ($scope.useSucc == false) {
                $scope.emailError = 'Tài khoản không tồn tại trong hệ thống';
                return;
            }

            if ($scope.useSucc == true && $scope.passSucc == false) {
                $scope.paswordError = 'Mật khẩu không chính xác';
                return;
            }

            /// thành công
            window.location.href = '#';
            // tiến hành duy trì và lưu thông tin đăng nhập cho những lần kế tiếp
            localStorage.setItem('Logged', JSON.stringify($scope.students));
            console.log(JSON.parse(localStorage.getItem('Logged')));


        } else if ($scope.login.username != '' && $scope.login.username != undefined && $scope.login.password != '' && $scope.login.password != undefined) {
            $http.get('/assets/db/Students.js').then(function (res) {
                $scope.students = res.data;
                const students = $scope.students;
                console.log(students);


                // Kiểm tra email
                for (let i = 0; i < students.length; i++) {
                    if (students[i].username === $scope.login.username) {
                        console.log('user name ok');
                        $scope.emailSuccess = true;
                        break;
                    }
                }

                // Kiểm tra tài khoản
                for (let i = 0; i < students.length; i++) {
                    if (students[i].username === $scope.login.username && students[i].password === $scope.login.password) {
                        console.log('tài khoản ok');
                        $scope.passwordSuccess = true;
                        break;
                    }
                }

                if ($scope.emailSuccess == false) {
                    $scope.emailError = 'Tài khoản không tồn tại trong hệ thống';
                    return;
                }

                if ($scope.emailSuccess == true && $scope.passwordSuccess == false) {
                    $scope.paswordError = 'Mật khẩu không chính xác';
                    return;
                }

                if ($scope.isChecked) {
                    $scope.students = {
                        miss: $scope.isChecked,
                        username: $scope.login.username,
                        password: $scope.login.password
                    }
                    localStorage.setItem("rememberAccount", JSON.stringify($scope.students));
                } else {
                    localStorage.removeItem('rememberAccount');
                }

                const data = JSON.parse(localStorage.getItem('rememberAccount'));
                console.log('Ghi nhớ tài khoản: ', data);



                $rootScope.isLogin = {
                    isLogin: true,
                    username: $scope.login.username,
                }

                // tiến hành duy trì và lưu thông tin đăng nhập cho những lần kế tiếp
                localStorage.setItem('Logged', JSON.stringify($scope.isLogin));
                console.log(JSON.parse(localStorage.getItem('Logged')));


                /// thành công
                window.location.href = '#';


            }, function (req) {
                alert('lỗi');
            });
        }
    }



    // Đăng ký
    $scope.dangKy = function () {

        $scope.password2Err = '';
        $scope.reSucc = false;

        if (localStorage.getItem('Account')) {
            let local = JSON.parse(localStorage.getItem('Account'));
            for (let i = 0; i < local.length; i++) {
                console.log(local[i].username);
                if ($scope.register.username === local[i].username) {
                    $scope.reSucc = true;
                    break;
                }
            }

            if ($scope.reSucc) {
                $scope.registerUserErr = 'Tài khoản đã tồn tại';
                return;
            } else {


                $scope.st =
                {
                    username: $scope.register.username,
                    password: $scope.register.password1,
                    fullname: "",
                    email: $scope.register.email,
                    gender: "",
                    birthday: "",
                    schoolfee: "",
                    marks: ""
                }

                // Tiến hành kiểm tra key có tồn tài chua
                if (localStorage.getItem('Account')) {
                    // tồn tại thì get ra và thêm vào
                    $scope.accountLocalStorage = JSON.parse(localStorage.getItem('Account'));
                    $scope.accountLocalStorage.push(angular.copy($scope.st));
                    // lưu dataa
                    localStorage.setItem('Account', JSON.stringify($scope.accountLocalStorage));
                    window.location.href = '#!login';

                } else {
                    // Không tồn tại tiến hàng tạo key
                    $scope.students.push(angular.copy($scope.st));
                    localStorage.setItem('Account', JSON.stringify($scope.students));
                    window.location.href = '#!login';
                }
            }
            return;
        }



        if ($scope.register.username != '' && $scope.register.username != undefined &&
            $scope.register.email != '' && $scope.register.email != undefined &&
            $scope.register.password1 != '' && $scope.register.password1 != undefined &&
            $scope.register.password2 != '' && $scope.register.password2 != undefined
        ) {

            if ($scope.register.password1 != $scope.register.password2) {
                $scope.password2Err = 'Mật khẩu xác nhận không chính xác';
                return;
            }

            $scope.registerSuccess = false;
            // Tiến hành get DB
            $http.get('/assets/db/Students.js').then(function (res) {
                $scope.students = res.data;
                const students = $scope.students;
                console.log(students);

                // Tiến hành kiểm tra tài khoản đã tồn tại hay chưa
                // Kiểm tra tài khoản
                for (let i = 0; i < students.length; i++) {
                    if (students[i].username === $scope.register.username) {
                        $scope.registerSuccess = true;
                        break;
                    }
                }

                $scope.registerUserErr = '';
                if ($scope.registerSuccess) {
                    $scope.registerUserErr = 'Tài khoản này đã tồn tại trên hệ thống';
                } else {


                    $scope.st =
                    {
                        username: $scope.register.username,
                        password: $scope.register.password1,
                        fullname: "",
                        email: $scope.register.email,
                        gender: "",
                        birthday: "",
                        schoolfee: "",
                        marks: ""
                    }

                    // Tiến hành kiểm tra key có tồn tài chua
                    if (localStorage.getItem('Account')) {
                        // tồn tại thì get ra và thêm vào
                        $scope.accountLocalStorage = JSON.parse(localStorage.getItem('Account'));
                        $scope.accountLocalStorage.push(angular.copy($scope.st));
                        // lưu dataa
                        localStorage.setItem('Account', JSON.stringify($scope.accountLocalStorage));
                        window.location.href = '#!login';

                    } else {
                        // Không tồn tại tiến hàng tạo key
                        $scope.students.push(angular.copy($scope.st));
                        localStorage.setItem('Account', JSON.stringify($scope.students));
                        window.location.href = '#!login';
                    }
                }


            }, function (req) {
                alert('lỗi');
            })

        }


    }



    $scope.clearErr = function () {
        $scope.emailError = '';
        $scope.paswordError = '';
        $scope.password2Err = '';
        $scope.registerUserErr = '';
        // alert('Hello');
    }



}
/**
 * End Validate Form
 */




