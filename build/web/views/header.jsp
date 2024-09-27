<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="header">
    <div class="header-left">
        <a href="#" class="logo">
            <img src="../assets/img/logo.png" alt="Logo"
                 onerror="this.onerror=null; this.src='./assets/img/logo.png';"
                 >
        </a>
        <a href="#" class="logo logo-small">
            <img src="../assets/img/logo-small.png" alt="Logo" width="30" height="30"
                 onerror="this.onerror=null; this.src='./assets/img/logo-small.png';"
                 >
        </a>
    </div>



    <div class="top-nav-search">
        <form>
            <input type="text" class="form-control" placeholder="Search here">
            <button class="btn" type="submit"><i class="fas fa-search"></i></button>
        </form>
    </div>

    <a class="mobile_btn" id="mobile_btn">
        <i class="fas fa-bars"></i>
    </a>

    <ul class="nav user-menu">

        <li class="nav-item dropdown has-arrow">
            <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                <span class="user-img">
                    <img class="rounded-circle" src="${sessionScope.user.avatar}" 
                         onerror="this.onerror=null; this.src='/images/user.png';"
                         width="31" alt="${sessionScope.user.fullName}"></span>
            </a>
            <div class="dropdown-menu">
                <div class="user-header">
                    <div class="avatar avatar-sm">                        
                        <img src="${sessionScope.user.avatar}" 
                             alt="User Image"  
                             onerror="this.onerror=null; this.src='/images/user.png';" 
                             class="avatar-img rounded-circle">
                    </div>
                    <div class="user-text">
                        <h6>${sessionScope.user.fullName}</h6>
                        <p class="text-muted mb-0">${sessionScope.user.getRole()}</p>
                    </div>
                </div>
                <c:if test="${sessionScope.user != null && sessionScope.user.avatar ne 'headmaster'}">
                    <a class="dropdown-item" href="/SWP/profile">My Profile</a>
                </c:if>
                <a class="dropdown-item" href="/SWP/logout">Logout</a>
            </div>
        </li>

    </ul>

</div>