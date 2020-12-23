<template>
  <v-container>
    <h1>회원 리스트</h1>
    <hr />
    <!-- 회원 검색 -->
    <v-row class="float-right">
      <v-col>
        <v-select
          :items="selectType"
          v-model="searchType"
          item-text="text"
          item-value="value"
          style="width: 120px;"
          dense
          outlined
        ></v-select>
      </v-col>

      <v-col>
        <v-text-field
          v-model="searchWord"
          placeholder="검색어를 입력..."
          single-line
          clearable
          dense
          solo
          style="width: 200px"
          @keypress.enter="searchHandler"
        ></v-text-field>
      </v-col>
      <v-col>
        <v-btn large depressed color="primary" @click="searchHandler"
          >검색</v-btn
        >
      </v-col>
    </v-row>
    <!-- 회원 리스트 출력 -->
    <v-row style="width: 100%; display: inline-block">
      <v-simple-table>
        <template v-slot:default>
          <colgroup>
            <col style="width: 20%; text-align: left;" />
            <col style="width: 20%; text-align: left;" />
            <col style="width: 30%; text-align: left;" />
            <col style="width: 20%; text-align: left;" />
            <col style="width: 10%; text-align: left;" />
          </colgroup>
          <thead>
            <tr>
              <th class="text-center">
                아이디
              </th>
              <th class="text-center">
                이름
              </th>
              <th class="text-center">
                관심 지역
              </th>
              <th class="text-center">
                직급
              </th>
              <th class="text-center">
                탈퇴
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(member, index) in members" :key="index">
              <td>{{ member.id }}</td>
              <td>{{ member.name }}</td>
              <td>{{ member.dong == "0" ? "없음" : member.dong }}</td>
              <td>{{ member.state == 1 ? "회원" : "관리자" }}</td>
              <td>
                <v-btn
                  v-if="member.state != 100"
                  large
                  depressed
                  color="primary"
                  @click="deleteMember(member.id)"
                >
                  탈퇴
                </v-btn>
              </td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "MemberList",
  data() {
    return {
      baseUrl: "http://localhost:8000/happyhouse",
      searchType: "",
      searchWord: "",
      selectType: [
        { text: "전체", value: "" },
        { text: "아이디", value: "id" },
        { text: "직급", value: "state" }
      ],
      members: []
    };
  },
  created() {
    this.searchHandler();
  },
  methods: {
    searchHandler() {
      if (this.searchWord == null) this.searchWord = "";
      let url = `${this.baseUrl}/mypage/list?key=${this.searchType}&word=${this.searchWord}`;
      axios
        .get(url)
        .then(({ data }) => {
          this.members = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다");
        });
    },
    deleteMember(id) {
      axios
        .delete(`${this.baseUrl}/mypage/delete/${id}`)
        .then(({ data }) => {
          if (data == "succ") alert(`${id} 탈퇴 성공`);
          this.searchHandler();
        })
        .catch(() => {
          alert(`탈퇴 실패`);
        });
    }
  }
};
</script>

<style scoped>
.float-right {
  float: right;
  margin: auto;
  text-align: center;
}
.text-center {
  text-align: center;
}
</style>
