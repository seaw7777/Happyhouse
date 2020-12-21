<template>
  <v-dialog v-model="l_close" persistent max-width="600px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        class="hidden-sm-and-down"
        text
        :color="btnColor"
        dark
        v-bind="attrs"
        v-on="on"
      >
        Login
      </v-btn>
    </template>
    <v-card>
      <v-card-title>
        <span class="headline">Login</span>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="ID* 를 입력해주세요"
                required
                v-model="user.id"
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Password* 를 입력해주세요"
                type="password"
                required
                @keypress.enter="login"
                v-model="user.pw"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="indigo darken-3" text @click="login">
          Login
        </v-btn>
        <v-btn color="indigo darken-3" text @click="l_close = false">
          CLOSE
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      btnColor: "black",
      l_close: false,
      user: {
        id: "",
        pw: ""
      },
      message: ""
    };
  },
  methods: {
    login: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch("LOGIN", this.user)
        .then(data => {})
        .catch(({ message }) => {
          this.message = message;
        });
    }
  }
};
</script>
