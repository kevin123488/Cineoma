<template>
  <div>
    <form class="container">
        <div class="mb-3">
            <label for="InputPassword" class="form-label">비밀번호</label>
            <input type="password" class="form-control" id="InputPassword" v-model="userPw">
        </div>
        <div class="mb-3">
            <label for="InputPasswordCheck" class="form-label">비밀번호 확인</label>
            <input type="password" class="form-control" id="InputPasswordCheck">
        </div>
        <div class="mb-3">
            <label for="InputNickname" class="form-label">닉네임</label>
            <input type="text" class="form-control" id="InputNickname" v-model="userNickname">
        </div>
        <div class="mb-3">
            <label for="InputEmail" class="form-label">Email</label>
            <input type="email" class="form-control" id="InputEmail" v-model="userEmail">
        </div>
        <button type="submit" class="btn btn-primary" @click="edit">변경</button>
    </form>
  </div>
</template>

<script>
export default {
    name: "editUserView",
    data() {
        return {
            userId: "",
            userPw: "",
            userNickname: "",
            userEmail: ""
        }
    },
    // methods: {
    //     async edit() {
    //         let newUser = {
    //             userId: this.$store.accounts.userId,
    //             userPw: this.userPw,
    //             userNickname: this.userNickname,
    //             userEmail: this.userEmail
    //         }
    //         console.log(newUser)

    //         await http
    //             .put(`/user/${newUser.userId}`, newUser)
    //             .then(
    //                 function (res) {
    //                      console.log(res)
    //             })
    //     }
    // },
    // created() {
    //     let userId = this.$store.accounts.userId
    //     http
    //         .get(`user/${userId}`)
    //         .then(({ data }) => {
    //             console.log(data)
    //             this.userId = data.userId
    //             this.userPw = data.userPw
    //             this.userNickname = data.userNickname
    //             this.userEmail = data.userEmail
    //         })
    // }

}
</script>

<style>

</style>