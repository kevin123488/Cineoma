  <template>
  <div>
    <!-- <account-error-list v-if="authError"></account-error-list> -->

<!--  -->
<!--  -->
<!--  -->

    <header class="u-clearfix u-header u-header bg-white" id="sec-56d0">
      <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
        <a class="u-image u-logo u-image-1">
          <router-link :to="{ name: 'lobby' }">
          <img src="homedesign/images/default-logo.png" class="u-logo-image u-logo-image-1">
          </router-link>
        </a>
        <nav class="u-menu u-menu-dropdown u-offcanvas u-menu-1">
          <div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0px;">
            <a class="u-button-style u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="#">
              <svg class="u-svg-link" viewBox="0 0 24 24"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#menu-hamburger"></use></svg>
              <svg class="u-svg-content" version="1.1" id="menu-hamburger" viewBox="0 0 16 16" x="0px" y="0px" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg"><g><rect y="1" width="16" height="2"></rect><rect y="7" width="16" height="2"></rect><rect y="13" width="16" height="2"></rect>
              </g></svg>
            </a>
          </div>
          <div class="u-nav-container">

            <!-- 큰화면 -->
            <ul class="u-nav u-unstyled u-nav-1">
              <li class="u-nav-item">
                <router-link :to="{ name: 'lobby' }" class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" style="font-family: 'NeoDunggeunmo Code';">로비</router-link>
              </li>
              <li class="u-nav-item">
                <router-link :to="{ name: 'profile', params: { id_pk: num } }" class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" style="font-family: 'NeoDunggeunmo Code';">마이페이지</router-link>
              </li>
              <li class="u-nav-item">
                <asd @click="logout" style="padding: 10px 20px; font-family: 'NeoDunggeunmo Code';">로그아웃</asd>
              </li>
            </ul>


          </div>
          <div class="u-nav-container-collapse">
            <!-- 작은화면 -->
            <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
              <div class="u-inner-container-layout u-sidenav-overflow">
                <div class="u-menu-close"></div>
                <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2">
                  
                  <li class="u-nav-item">
                    <p class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base m-0">
                      <router-link :to="{ name: 'lobby' }">로비</router-link>
                    </p>
                  </li>
                  
                  <li class="u-nav-item">
                    <p class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base ">
                      <router-link :to="{ name: 'profile', params: { id_pk: num } }">마이페이지</router-link>
                    </p>
                  </li>
                  
                  <li class="u-nav-item">
                    <p class="w3-bar-item w3-button" @click="logout">로그아웃</p>
                  </li>
                  </ul>
              </div>
            </div>
            <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
          </div>
        </nav>
      </div>
    </header>


<!--  -->
<!--  -->
<!--  -->
    <!-- <nav class="w3-bar w3-black w3-hide-small">
      <ul>
        <router-link :to="{ name: 'lobby' }" 
           class="w3-bar-item w3-button">로비</router-link>
        <router-link :to="{ name: 'profile', params: { id_pk: num } }" 
           class="w3-bar-item w3-button">마이페이지</router-link>
        <div class="w3-bar-item w3-button" @click="logout">로그아웃</div>
      </ul>
    </nav> -->

  </div>
</template>

<script>

  export default {
    name: 'NavHeader',
    components: {
    },
    data() {
      return {
        num : 1,
      }
    },
    computed: {
    },
    methods: {
      logout() {
        this.$store.commit("accounts/SET_LOGIN", {
          isLogin: false,
          username: "",
          id: "",
          email: "",
          nickname: "",
          password: "",
        })
        alert("로그아웃!!")
        this.$router.push("/") // 로그아웃 하면 로그인 창으로 보내주자
      },
    }}

</script>

<style>
  .w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-button {
    display: inline-block;
    height: 40px;
  } 
  .w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-right {
    height: 40px;
    padding-top: 6px;
    padding-right: 5px;
    margin: 0 0;
  }
  .w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-right > i {
    position: relative;
    top: -2px;
  }
  .w3-bar.w3-black.w3-hide-small .fa {
    /* height: 30px; */
    margin: 0;
    /* padding-top: 6px; 
    margin: 0 0; */
  }
  .auto-parent {
    position: absolute; 
    top: 45px; right:20px; 
    height: 50px; width: 50px;
  }
  .auto {
    position: absolute; 
    top: -16px; left: -201px;
  }
  .auto > .auto-child:nth-child(1) {
    padding-top: 3px;
  }
  .auto-child {
    padding-left: 4px;
    padding-top: 1px;
    width: 220px;
  }
</style>